"""
Authors: Federico Melo Barrero.
            Sebastian Contreras.

Basic idea: Read program as a single string where every token is separated by
            exactly one space.
            Orderly check the string for sintax errors, one part of the
            program at a time. Every time a part has been checked, it's
            removed from the string. As a result, as the parser executes,
            the string it's processing becomes progressively shorter until
            it's empty.

"""

from re import sub  # Regular expressions
from re import split as rsplit


def load_program(file_name: str) -> None:
    """Loads program as a single string.
       Subtitutes every whitespace character, including newlines and tabs,
       by a single space.

    Args:
        file_name (str): name of the file with the program to check
    """
    program = open(file_name)
    program_str = program.read()
    program.close()

    # Convert newlines, tabs and multiple spaces to a single space
    program_str = sub(r"\s+", " ", program_str)

    check_sintax(program_str.strip())


def raise_sintax_error(error: str) -> None:
    """Ends sintax check after finding a sintax error.
       Prints False to inform the program is incorrect and stops execution.
    """
    print("Sintax error: " + error)  # TODO: delete line
    print(False)
    quit()


def check_sintax(program_str: str) -> bool:
    """ Uses various auxiliary functions to check if a program is correctly
        written. 'A program defintion begins with the keyword PROG possibly
        followed by a declaration of variables, followed by zero or more
        procedure defintions, followed by a block of instructions.
        It ends the keyword GORP'.

    Args:
        program_str (str): _description_

    Returns:
        bool: _description_
    """

    # PROG and GORP
    if program_str[0:4] != "PROG":
        raise_sintax_error("expected 'PROG' to begin program definition.")
    if program_str[-4:] != "GORP":
        raise_sintax_error("expected 'GORP' to end program definition.")
    program_str = program_str[4:-4].strip()

    # Variable declaration
    var_names = []
    if program_str[0:3] == "VAR":
        program_str, var_names = check_variable_declaration(program_str)
    variables = {}  # variables = {<var_name>: <value>}
    for var_name in var_names:
        variables[var_name] = None  # Variables are assigned None by default

    # Procedure definitions
    procedures = []
    # procedures = [[<name>, [(<param_name>, <type>)]], [],...]
    # Element e.g: ["run", [("O", "NUM_VAR_PARAM") , ("n", "NUM_VAR_PARAM")]]
    while program_str.strip()[0:4] == "PROC":
        updated = check_proc_declaration(program_str.strip(), procedures,
                                         variables)
        program_str, procedures, variables = updated

    # Final block of instructions
    variables = check_instruction_block(program_str.strip(),
                                        variables,
                                        procedures)

    # If no exceptions have been raised by this point, sintax is correct
    print(True)


def check_variable_declaration(program_str: str) -> str:
    """Called if a variable declaration follows PROG keyword.
    'A declaration of variables is the keyword VAR followed by a list of names
     separated by commas. [...] The list is followed by ;.'

    Args:
        program_str (str): _description_
    """
    pos_var_declaration_end = program_str.find(";")
    if pos_var_declaration_end == -1:
        raise_sintax_error("expected ';' to end variable declaration.")

    # Remove VAR and ;
    var_declaration = program_str[3:pos_var_declaration_end].strip()

    # Check if there's at least one comma
    if var_declaration.find(",") == -1:
        raise_sintax_error("expected ',' to separate variables.")
    var_list = var_declaration.split(",")
    var_names = []
    for var in var_list:
        check_name(var.strip())
        var_names.append(var.strip())

    program_str = program_str[pos_var_declaration_end+1:].strip()
    return program_str, var_names


def check_name(name: str) -> None:
    """Checks names, both for variables and procedures.
    'A name is a string of alphanumeric characters that begins with a letter.'

    Args:
        name (str): name to check
    """
    # First character
    if not name[0].isalpha():  # name doesn't begin with letter
        raise_sintax_error("name '"+name+"' doesn't begin with a letter.")

    # Other characters
    if len(name) > 1:
        for char in name[1:]:
            if not (char.isalpha or char.isnumeric):  # one isn't alphanumeric
                raise_sintax_error("name '"+name+"' isn't alphanumeric.")


def check_proc_declaration(program_str: str, procedures: list,
                           variables: dict) -> tuple:
    """Check a procedure declaration.

    Args:
        procedures (dict):
        program_str (str): What is left of the program as a string
        variables (dict):

    Returns:
        tuple: updated procedures dictionary, what is left of the program
               as a string after the check and variables dictionary
    """

    # Check for CORP. Remove PROC and CORP
    pos_procedure_end = program_str.find("CORP")
    if pos_procedure_end == -1:
        raise_sintax_error("expected 'CORP' to end procedure definition.")
    procedure = program_str[4:pos_procedure_end].strip()
    program_str = program_str[pos_procedure_end+4:].strip()

    # Extract and check procedure name
    pos_end_procedure_name = procedure.find(" ")
    if pos_end_procedure_name == -1:
        raise_sintax_error("expected blankspace character after procedure " +
                           "name.")
    procedure_name = procedure[:pos_end_procedure_name]
    procedure_name = procedure_name.strip()
    check_name(procedure_name)
    procedure = procedure[pos_end_procedure_name:].strip()

    # Extract parameters.

    if procedure[0] != "(":
        raise_sintax_error("expected '(' before list of parameters in " +
                           "procedure '"+procedure_name+"'.")

    end_parenthesis = procedure.find(")")
    if end_parenthesis == -1:
        raise_sintax_error("expected ')' after list of parameters in " +
                           "procedure '"+procedure_name+"'.")

    if procedure[1] == ")" or (procedure[1] == " " and procedure[2] == ")"):
        # Looking for '()' or '( )'. If found, there's no parameters
        parameters = []
        proc_param_names = []
    else:
        parameters = []
        proc_param_names = []
        parameters_pre_strip = procedure[1:end_parenthesis].split(",")
        for parameter in parameters_pre_strip:
            check_name(parameter.strip())  # Parameters are names
            parameters.append((parameter.strip(), "NUM_VAR_PARAM"))
            proc_param_names.append(parameter.strip())

    # Add procedure name and parameters to procedures
    procedures.append([procedure_name, parameters])

    # Instructions
    instructions_block = procedure[end_parenthesis+1:].strip()
    variables = check_instruction_block(instructions_block.strip(),
                                        variables, procedures,
                                        procedure_name, proc_param_names)

    return (program_str, procedures, variables)


def check_instruction_block(block: str, variables: dict, procedures: list,
                            procedure_name="", proc_param_names=[]) -> list:
    """Checks the sintax for a block of instructions
    'A block of instructions is a sequence of instructions separated
    by semicolons within curly brackets'.
    """

    # Write error message. Differs if the instruction block belongs to a
    # procedure
    no_start_curly_msg = "Expected '{' before "
    no_end_curly_msg = "Expected '}' after "
    if procedure_name == "":  # Final instruction block, no procedure name
        no_start_curly_msg += "the last block of instructions."
        no_end_curly_msg += "the last block of instructions."
    else:
        no_start_curly_msg += ("block of instructions in procedure '" +
                               procedure_name + "'.")
        no_end_curly_msg += ("block of instructions in procedure '" +
                             procedure_name + "'.")

    # Check curly brackets
    if block[0] != "{":
        raise_sintax_error(no_start_curly_msg)
    if block[-1] != "}":
        raise_sintax_error(no_end_curly_msg)
    block = block[1:-1].strip()

    # There's no checking for ';' beforehand, as there can be just a single
    # instruction and hence no ';'
    instructions = block.split(";")
    for instruction in instructions:
        variables = check_instruction(instruction.strip(), variables,
                                      procedures,
                                      procedure_name,
                                      proc_param_names)
    return variables


def check_instruction(instruction: str, variables: dict, procedures: list,
                      procedure_name: str, proc_param_names: list) -> None:
    """Check the sintax for a given instruction is valid
     'An instruction can be a command, a control structure or a procedure
      call'.
    """

    command_names = ["walk", "jump", "jumpTo", "veer", "look", "drop",
                     "grab", "get", "free", "pop"]
    procedure_names = [list_proc[0] for list_proc in procedures]
    control_structure_names = ["if", "while", "repeatTimes"]

    # Tokenize instruction
    between_non_alphanumeric = r'(?=[^A-Za-z0-9\s])(?<=[^A-Za-z0-9\s])'
    instr_tokens_ = rsplit(r'(\b|\s|'+between_non_alphanumeric+r')',
                           instruction)
    instr_tokens = [e for e in instr_tokens_ if e != "" and e != " "]

    first_token = instr_tokens[0]
    if first_token in list(variables.keys()):
        variables = check_var_assignment(instr_tokens, variables)
    elif first_token in command_names:
        check_command(instr_tokens, variables, proc_param_names)
    elif first_token in control_structure_names:
        variables = check_control_structure(instr_tokens, variables,
                                            proc_param_names, procedures,
                                            procedure_name)
    elif first_token in procedure_names:
        pass
        # TODO: check_procedure_call(instr_tokens, procedures)
    else:
        raise_sintax_error("Name '"+first_token+"' is not defined.")

    return variables


def check_var_assignment(instr_tokens: list, variables: dict) -> None:
    var_name = instr_tokens[0]

    # Supports both '=' and ':=' for assignments. TODO: Ask!
    if instr_tokens[1] == "=":
        asign_operator = "="
        value = instr_tokens[2]
    elif instr_tokens[1] == ":" and instr_tokens[2] == "=":
        asign_operator = ":="
        value = instr_tokens[3]
    else:
        raise_sintax_error("Expected '=' or ':=' as assignment operator " +
                           "for variable '" + var_name + "'.")

    # Check if assigned value is numeric
    try:
        value = float(value)
    except ValueError:
        raise_sintax_error("Expected a number after assignment " +
                           "operator '"+asign_operator +
                           "' for variable '"+var_name+"'.")
    variables[var_name] = value

    return variables


def check_command(instr_tokens: list, variables: dict,
                  parameters: list) -> None:
    command = instr_tokens[0]

    # Single-parameter commands
    single_param_commands = ["jump", "veer", "look", "drop", "grab",
                             "get", "free", "pop"]
    if command in single_param_commands:
        if len(instr_tokens) != 4:
            raise_sintax_error("Expected exactly one parameter for '" +
                               command + "' command.")
        else:
            param = instr_tokens[2]

            # Commands that recieve a single parameter, that may be a
            # number, a variable, or a parameter.
            num_var_param_commands = ["jump", "drop", "grab", "get",
                                      "free", "pop"]
            if command in num_var_param_commands:
                check_param_is_number_var_or_param(param, variables,
                                                   parameters, command)

            # Other single-parameter commands
            elif command == "veer":  # veer(D)
                veer_dirs = ["left", "right", "around"]
                if param not in veer_dirs:
                    raise_sintax_error("Expected 'left', 'right', or " +
                                       "'around' as parameters for 'veer' " +
                                       "command.")
            elif command == "look":  # look(O)
                cardinal_dirs = ["north", "south", "east", "west"]
                if param not in cardinal_dirs:
                    raise_sintax_error("Expected 'north', 'south', 'east', " +
                                       "or 'west' as parameters for 'look' " +
                                       "command.")

    # Double-parameter commands
    # jumpTo(n,m) is the only double-parameter command with no homonyms
    elif command == "jumpTo":
        if len(instr_tokens) == 6:
            param1, param2 = instr_tokens[2], instr_tokens[4]
            check_param_is_number_var_or_param(param1, variables,
                                               parameters, command)
            check_param_is_number_var_or_param(param2, variables,
                                               parameters, command)
        else:
            raise_sintax_error("Expected exactly two parameters for " +
                               "'jumpTo' command.")

    # Walk command has both single- and double-parameter versions
    elif command == "walk":
        if len(instr_tokens) == 4:  # walk(n)
            param = instr_tokens[2]
            check_param_is_number_var_or_param(param, variables,
                                               parameters, command)
        elif len(instr_tokens) == 6:  # walk(d, n) or walk(o, n)
            param1, param2 = instr_tokens[2], instr_tokens[4]
            check_param_is_number_var_or_param(param2, variables,
                                               parameters, command)
            walk_dirs = ["front", "right", "left", "back"]
            cardinal_dirs = ["north", "south", "east", "west"]
            if not (param1 in walk_dirs or param1 in cardinal_dirs):
                raise_sintax_error("Expected a direction for 'walk' " +
                                   "command. Invalid parameter '"+param1+"'.")
        else:
            raise_sintax_error("Expected exactly one or two parameters " +
                               "for 'walk' command.")

    # If this else clause runs, command_names list in check_instruction is
    # inconsistent with commands handled here
    else:
        raise_sintax_error("Command name '"+command+"' is not recognized.")


def check_param_is_number_var_or_param(param_to_check: str, variables: dict,
                                       parameters: list,
                                       command: str) -> None:
    try:
        float(param_to_check)
    except ValueError:
        is_var = param_to_check in list(variables.keys())
        is_param = param_to_check in parameters
        condition = is_var or is_param
        if not condition:
            raise_sintax_error("Parameter '"+param_to_check+"' in command '" +
                               command+"' is not a number, variable or " +
                               "parameter.")


def check_control_structure(instr_tokens: list, variables: dict,
                            parameters: list, procedures: list,
                            procedure_name: str) -> str:
    ctrl_struc_name = instr_tokens[0]
    ctrl_struc_end = instr_tokens[-1]

    # Conditional: if
    if ctrl_struc_name == "if":
        if ctrl_struc_end != "fi":
            raise_sintax_error("Expected 'fi' to end 'if' control structure.")

        # From tokens to string
        if_string = " ".join(instr_tokens[1:-1]).strip()

        if "else" in instr_tokens:  # if (condition)Block1 else Block2 fi
            variables = check_if_else(if_string, variables, parameters,
                                      procedures, procedure_name)
        else:  # if (condition)Block1 fi
            variables = check_if_fi(if_string, variables, parameters,
                                    procedures, procedure_name)

    # Loop: while do
    elif ctrl_struc_name == "while":
        if ctrl_struc_end != "od":
            raise_sintax_error("Expected 'od' to end 'while .. do' " +
                               "control structure.")

        # From tokens to string
        while_string = " ".join(instr_tokens[1:-1]).strip()
        variables = check_while(while_string, variables, parameters,
                                procedures, procedure_name)

    # Repeat: repeatTimes
    elif ctrl_struc_name == "repeatTimes":
        if ctrl_struc_end != "per":
            raise_sintax_error("Expected 'per' to end 'repeatTimes' " +
                               "control structure.")

        # From tokens to string
        repeat_string = " ".join(instr_tokens[1:-1]).strip()
        variables = check_repeat(repeat_string, variables, parameters,
                                 procedures, procedure_name)

    else:  # This else clause should never run!
        raise_sintax_error("Error in procedure call for procedure '" +
                           ctrl_struc_name+"'.")
    return variables


def check_if_fi(if_string: str, variables: dict,
                parameters: list, procedures: list,
                procedure_name: str) -> list:
    """
    if (condition)Block1 fi
    (condition)Block1
    """
    ctrl_struc_name = "if"
    if_string = check_condition(if_string, variables,
                                parameters, procedures,
                                procedure_name, ctrl_struc_name)
    # TODO: check_if_fi
    # begin_block1 = instr_tokens.index(")")
    # instruc_block_tokens = instr_tokens[begin_block1+1:-1]
    # instruc_block = " ".join(instruc_block_tokens)
    # variables = check_instruction_block(instruc_block.strip(),
    #                                     variables,
    #                                     procedures,
    #                                     procedure_name, parameters)
    return variables


def check_if_else(if_else_string: str, variables: dict,
                  parameters: list, procedures: list,
                  procedure_name: str) -> list:
    """
    if (condition)Block1 else Block2 fi
    (condition)Block1 else Block2
    """
    ctrl_struc_name = "if ... else"
    if_else_string = check_condition(if_else_string, variables,
                                     parameters, procedures,
                                     procedure_name, ctrl_struc_name)
    # TODO: check_if_else
    # instruc_block1 = " ".join(block1_tokens)
    # variables = check_instruction_block(instruc_block1.strip(),
    #                                     variables,
    #                                     procedures,
    #                                     procedure_name, parameters)
    # block2_tokens = instr_tokens[end_block1+1:-1]
    # instruc_block2 = " ".join(block2_tokens)
    # variables = check_instruction_block(instruc_block2.strip(),
    #                                     variables,
    #                                     procedures,
    #                                     procedure_name, parameters)
    return variables


def check_while(while_str: str, variables: dict,
                parameters: list, procedures: list,
                procedure_name: str) -> list:
    """
    while (condition)do Block od
    while_string: (condition)do Block
    """
    ctrl_struc_name = "while"
    while_str = check_condition(while_str, variables,
                                parameters, procedures,
                                procedure_name, ctrl_struc_name)

    if while_str[:2] != "do":
        raise_sintax_error("Expected 'do' for 'while .. do' " +
                           "control structure.")
    while_str = while_str[2:].strip()
    variables = check_instruction_block(while_str,
                                        variables, procedures,
                                        procedure_name, parameters)
    return variables


def check_repeat(repeat_string: str, variables: dict,
                 parameters: list, procedures: list,
                 procedure_name: str) -> list:
    """
    repeatTimes n Block per
    n Block
    """
    # TODO: check_repeat
    return variables


def check_condition(ctrl_struc_str: str, variables: dict,
                    parameters: list, procedures: list,
                    procedure_name: str, ctrl_struc_name: str,
                    look_for_extra_parenthesis=False) -> str:
    """
        ctrl_struc_str (str): There's always a single space between tokens
    """
    # Identify procedure for error message
    error_msg_end_ = ""
    if procedure_name != "":
        error_msg_end_ = " in procedure '"+procedure_name+"'"
    in_err_msg = (" in '"+ctrl_struc_name+"' control structure" +
                  error_msg_end_+".")

    # Search for '(' in (condition)
    condition_start = ctrl_struc_str.find("(")
    if condition_start == -1:
        raise_sintax_error("Expected '(' to start condition"+in_err_msg)

    ctrl_struc_str = ctrl_struc_str[condition_start+1:].strip()

    conditions = ["isfacing", "isValid", "canWalk", "not"]
    condition_name_end = ctrl_struc_str.find(' ')
    condition_name = ctrl_struc_str[:condition_name_end]
    if condition_name not in conditions:
        raise_sintax_error("Condition name '"+condition_name+"' not " +
                           "defined"+in_err_msg)

    if condition_name == "not":
        nested_condition = ctrl_struc_str[3:].strip()
        ctrl_struc_str = check_condition(nested_condition, variables,
                                         parameters, procedures,
                                         procedure_name, ctrl_struc_name,
                                         True)
        # True parameter removes second parenthesis in 'not ( condition )'
        # TODO

    ctrl_struc_str = ctrl_struc_str[condition_name_end+2:].strip()
    parameter_separator = ctrl_struc_str.find(" ")
    param1 = ctrl_struc_str[:parameter_separator].strip()

    # Conditions
    double_parameter = ["isValid", "canWalk"]

    # Only single-parameter condition (except 'not'): 'isfacing'
    isfacing_dirs = ["north", "south", "east", "west"]
    if condition_name == "isfacing":
        if ctrl_struc_str[parameter_separator+1] != ')':
            raise_sintax_error("Expected exactly one parameter for '" +
                               condition_name+"' condition"+in_err_msg)
        if param1 not in isfacing_dirs:
            raise_sintax_error("Invalid parameter '"+param1+"' for '" +
                               condition_name+"' condition"+in_err_msg)
        # '( isfacing ( north ) ) ...' -> ') ...'
        ctrl_struc_str = ctrl_struc_str[parameter_separator+2:].strip()
    elif condition_name in double_parameter:
        comma = ctrl_struc_str.find(",")
        if comma == -1:
            raise_sintax_error("Expected ',' to separate parameters in '" +
                               condition_name+"' condition"+in_err_msg)
        ctrl_struc_str = ctrl_struc_str[comma+1:].strip()
        closing_parenthesis = ctrl_struc_str.find(")")
        if closing_parenthesis == -1:
            raise_sintax_error("Expected ')' to enclose parameters in '" +
                               condition_name+"' condition"+in_err_msg)
        param2 = ctrl_struc_str[:closing_parenthesis].strip()
        # '( canWalk ( north , 1 ) ) ...' -> ') ...'
        ctrl_struc_str = ctrl_struc_str[closing_parenthesis+2:].strip()

        ins = ["walk", "jump", "grab", "pop", "pick", "free", "drop"]
        walk_d = ["north", "south", "east", "west"]
        walk_o = ["right", "left", "front", "back"]
        # isValid(ins,n)
        if condition_name == "isValid":
            if param1 not in ins:
                raise_sintax_error("Invalid parameter '"+param1 +
                                   "' for '"+condition_name +
                                   "' condition"+in_err_msg)
            check_param_is_number_var_or_param(param2, variables, parameters,
                                               param1)

        # canWalk(d,n) or canWalk(o,n)
        elif condition_name == "canWalk":
            if not(param1 in walk_d or param1 in walk_o):
                raise_sintax_error("Invalid parameter '"+param1 +
                                   "' for '"+condition_name +
                                   "' condition"+in_err_msg)
            check_param_is_number_var_or_param(param2, variables, parameters,
                                               "canWalk")

    if ctrl_struc_str[0] != ")":
        raise_sintax_error("Expected ')' to enclose '" +
                           condition_name+"' condition"+in_err_msg)
    ctrl_struc_str = ctrl_struc_str[1:].strip()
    if look_for_extra_parenthesis:
        if ctrl_struc_str[0] != ")":
            raise_sintax_error("Expected ')' to enclose '" +
                               condition_name+"' condition"+in_err_msg)
        ctrl_struc_str = ctrl_struc_str[1:].strip()
    return ctrl_struc_str


def check_procedure_call(instr_tokens: list, procedures: dict) -> None:
    pass


def main() -> None:
    # file_name = "program.txt"
    file_name = "wrong_program.txt"
    load_program(file_name)


main()
