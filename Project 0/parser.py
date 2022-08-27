# Authors: Federico Melo Barrero.
#          Sebastian Contreras.


import re

def load_program(file_name: str)->None:
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


def check_sintax(program_str: str)->bool:
    """ Uses various auxiliary functions to check if a program is correctly written.
        'A program defintion begins with the keyword PROG possibly followed by a declaration
        of variables, followed by zero or more procedure defintions, followed by a block of
        instructions. It ends the keyword GORP'.

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
    if program_str[0:3] == "VAR":
        program_str = check_variable_declaration(program_str)
    
    # Procedure definitions
    procedures = []  # List of tuples with structure (<procedure_name>, <parameter_count>)
    while program_str.strip()[0:4] == "PROC":
        procedures, program_str = check_procedure(procedures, program_str.strip())

    # Final block of instructions
    check_instruction_block(program_str.strip())

    # Once the parser gets here, if no exceptions have been raised, sintax is correct
    print(True)


def check_variable_declaration(program_str: str)->str:
    """Called if a variable declaration follows PROG keyword.
    'A declaration of variables is the keyword VAR followed by a list of names separated
     by commas. [...] The list is followed by ;.'

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
    for var in var_list:
        check_name(var.strip())

    program_str = program_str[pos_var_declaration_end+1:].strip()
    return program_str


def check_name(name: str)->None:
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
            if not (char.isalpha or char.isnumeric):  # at least one isn't alphanumeric
                sintax_error("name '"+name+"' isn't alphanumeric.")


def check_procedure(procedures: list, program_str: str)->tuple:
    """Check a procedure.

    Args:
        procedures (list): List of tuples in which each tuple has the form (procedure_name, parameter_count)
        program_str (str): What is left of the program as a string

    Returns:
        tuple: List of tuples with information on procedures and what is left of the program as a string
    """

    # Check for CORP. Remove PROC and CORP
    pos_procedure_end = program_str.find("CORP")
    if pos_procedure_end == -1:
        sintax_error("expected 'CORP' to end procedure definition.")
    procedure = program_str[4:pos_procedure_end].strip()
    program_str = program_str[pos_procedure_end+4:].strip()

    # Extract procedure name
    pos_end_procedure_name = procedure.find(" ")
    if pos_end_procedure_name == -1:
        sintax_error("expected blankspace character after procedure name.")
    procedure_name = procedure[:pos_end_procedure_name]
    check_name(procedure_name.strip())
    procedure = procedure[pos_end_procedure_name:].strip()

    # Extract parameters.
    if procedure[0] != "(":
        sintax_error("expected '(' before list of parameters in procedure '"+procedure_name+"'.")
    end_parenthesis = procedure.find(")")
    if end_parenthesis == -1:
        sintax_error("expected ')' after list of parameters in procedure '"+procedure_name+"'.")
    elif end_parenthesis == 1 or end_parenthesis == 2:
        # If ) is in second on third position, () or ( ), there's no parameters
        parameter_count = 0
    else:
        if procedure.find(",") == -1:
            sintax_error()
        parameters_not_split = procedure[1:end_parenthesis].split(",")
        parameters = [p.strip() for p in parameters_not_split]
        for parameter in parameters:
            check_name(parameter)  # TODO: Are parameters names?
        parameter_count = len(parameters)
    
    instructions_block = procedure[end_parenthesis+1:].strip()

    # Add procedure name and parameters count to procedures
    procedures.append((procedure_name, parameter_count))

    # Instructions
    check_instruction_block(instructions_block.strip(), procedure_name)

    return (procedures, program_str)


def check_instruction_block(block: str, procedure_name="")->None:
    """Checks the sintax for a block of instructions
    'A block of instructions is a sequence of instructions separated 
    by semicolons within curly brackets'.

    Args:
        block (str): _description_
    """

    # Write error message. Differs if the instruction block belongs to a procedure
    if procedure_name == "":  # Final instruction block, no procedure name
        no_start_curly_msg = "Expected '{' before the last block of instructions."
        no_end_curly_msg = "Expected '}' after the last block of instructions."
    else:
        no_start_curly_msg = "Expected '{' before block of instructions in procedure '"+procedure_name+"'."
        no_end_curly_msg = "Expected '}' after block of instructions in procedure '"+procedure_name+"'."
    
    # Check curly brackets
    if block[0] != "{":
        sintax_error(no_start_curly_msg)
    if block[-1] != "}":
        sintax_error(no_end_curly_msg)
    block = block[1:-1].strip()

    # No checking for ';' beforehand, as there can be just a single instruction and hence no ';'
    instructions = block.split(";")
    for instruction in instructions:
        check_instruction(instruction.strip())
    # TODO: Falta algo más después de revisar cada instrucción?
    pass


def check_instruction(instruction: str)->None:
    """Check the sintax for a given instruction is valid
     'An instruction can be a command, a control structure or a procedure call'.
    """
    # TODO: Revisar instrucción!
    pass


def sintax_error(error: str)->None:
    """Ends sintax check after finding a sintax error.
       Prints False to inform the program is incorrect and stops execution.
    """
    print("Sintax error: "+error) # TODO: delete line
    print(False)
    quit()


def main()->None:
    # file_name = "program.txt"
    file_name = "wrong_program.txt"
    load_program(file_name)


main()