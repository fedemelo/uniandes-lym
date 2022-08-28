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

import re

commands_num_var_or_param = {'walk': 1, "jump": 1, "jumpTo": 2, "drop": 1,
                             'grab': 1, "get": 1, "free": 1, "pop": 1}


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
    program_str = re.sub("\s+", " ", program_str)

    check_sintax(program_str.strip())


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
        sintax_error("expected 'PROG' to begin program definition.")
    if program_str[-4:] != "GORP":
        sintax_error("expected 'GORP' to end program definition.")
    program_str = program_str[4:-4].strip()

    # Variable declaration
    var_names = []
    if program_str[0:3] == "VAR":
        program_str, var_names = check_variable_declaration(program_str)

    # Procedure definitions
    procedures = {}  # {<name>: [<parameters>]}
    while program_str.strip()[0:4] == "PROC":
        program_str, procedures = check_procedure(program_str.strip(),
                                                  procedures, var_names)

    # Final block of instructions
    check_instruction_block(program_str.strip(), var_names, procedures)

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
        sintax_error("expected ';' to end variable declaration.")

    # Remove VAR and ;
    var_declaration = program_str[3:pos_var_declaration_end].strip()

    # Check if there's at least one comma
    if var_declaration.find(",") == -1:
        sintax_error("expected ',' to separate variables.")
    var_list = var_declaration.split(",")
    var_names = []
    for var in var_list:
        check_name(var.strip())
        var_names.append(var)

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
        sintax_error("name '"+name+"' doesn't begin with a letter.")

    # Other characters
    if len(name) > 1:
        for char in name[1:]:
            if not (char.isalpha or char.isnumeric):  # one isn't alphanumeric
                sintax_error("name '"+name+"' isn't alphanumeric.")


def check_procedure(program_str: str, procedures: dict,
                    var_names: list) -> tuple:
    """Check a procedure.

    Args:
        procedures (dict): Dictionary with procedure names as keys and
                           parameter lists as values
                           {<procedure_name>: [<parameters>]}
        program_str (str): What is left of the program as a string
        var_names (list): Names of the variables defined for the program.
                          Necessary to check instruction blocks.

    Returns:
        tuple: updated procedures dictionary and what is left of the program
               after the check as a string
    """

    # Check for CORP. Remove PROC and CORP
    pos_procedure_end = program_str.find("CORP")
    if pos_procedure_end == -1:
        sintax_error("expected 'CORP' to end procedure definition.")
    procedure = program_str[4:pos_procedure_end].strip()
    program_str = program_str[pos_procedure_end+4:].strip()

    # Extract and check procedure name
    pos_end_procedure_name = procedure.find(" ")
    if pos_end_procedure_name == -1:
        sintax_error("expected blankspace character after procedure name.")
    procedure_name = procedure[:pos_end_procedure_name]
    check_name(procedure_name.strip())
    procedure = procedure[pos_end_procedure_name:].strip()

    # Extract parameters.

    if procedure[0] != "(":
        sintax_error("expected '(' before list of parameters in procedure '" +
                     procedure_name+"'.")

    end_parenthesis = procedure.find(")")
    if end_parenthesis == -1:
        sintax_error("expected ')' after list of parameters in procedure '" +
                     procedure_name+"'.")

    if procedure[1] == ")" or (procedure[1] == " " and procedure[2] == ")"):
        # Looking for '()' or '( )'. If found, there's no parameters
        parameters = []
    else:
        parameters_pre_strip = procedure[1:end_parenthesis].split(",")
        parameters = [p.strip() for p in parameters_pre_strip]
        for parameter in parameters:
            check_name(parameter)  # Parameters are names, just as variables

    # Add procedure name and parameters to procedures
    procedures[procedure_name] = parameters
    # FIXME: Ojo! Dos procedimientos con mismo nombre, ahí qué hago??
    # Posible solución: que la llave sea una concatenación nombre y parámetros.

    # Instructions
    instructions_block = procedure[end_parenthesis+1:].strip()
    check_instruction_block(instructions_block.strip(), var_names, procedures,
                            procedure_name)

    return (program_str, procedures)


def check_instruction_block(block: str, var_names: list, procedures: dict,
                            procedure_name="") -> None:
    """Checks the sintax for a block of instructions
    'A block of instructions is a sequence of instructions separated
    by semicolons within curly brackets'.

    Args:
        block (str): _description_
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
        sintax_error(no_start_curly_msg)
    if block[-1] != "}":
        sintax_error(no_end_curly_msg)
    block = block[1:-1].strip()

    # There's no checking for ';' beforehand, as there can be just a single
    # instruction and hence no ';'
    instructions = block.split(";")
    for instruction in instructions:
        check_instruction(instruction.strip(), var_names, procedures,
                          procedure_name)


def check_instruction(instruction: str, var_names: list, procedures: list,
                      procedure_name: str) -> None:
    """Check the sintax for a given instruction is valid
     'An instruction can be a command, a control structure or a procedure
      call'.
    """

    # Control structure

    # Procedure call

    # TODO: Revisar instrucción!
    pass


def check_command(instruction: str) -> str:
    pass


def check_param_is_num_var_or_param(param: str, vars: list,
                                    procedure_params: list) -> None:
    """
    Checks if a parameter in a command is a number, a previously defined
    variable or, if the command is called within a procedure, a parameter
    of said procedure.

    Args:
        parameter (str): parameter in a command
    """


def sintax_error(error: str) -> None:
    """Ends sintax check after finding a sintax error.
       Prints False to inform the program is incorrect and stops execution.
    """
    print("Sintax error: "+error)  # TODO: delete line
    print(False)
    quit()


def main() -> None:
    # file_name = "program.txt"
    file_name = "wrong_program.txt"
    load_program(file_name)


main()
