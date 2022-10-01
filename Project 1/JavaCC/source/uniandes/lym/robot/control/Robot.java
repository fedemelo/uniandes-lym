/* Generated By:JavaCC: Do not edit this line. Robot.java */
package uniandes.lym.robot.control;

import uniandes.lym.robot.kernel.*;
import uniandes.lym.robot.view.Console;

import java.awt.Point;
import java.io.*;
import java.util.Vector;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Robot implements RobotConstants {
        private RobotWorldDec world;


        void setWorld(RobotWorld w) {
                world = (RobotWorldDec) w;
        }


        String salida = new String();

  final public boolean program(Console sistema) throws ParseException {
                salida = new String();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PROG:
      jj_consume_token(PROG);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        varDecl();
        break;
      default:
        jj_la1[0] = jj_gen;
        ;
      }
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PROC:
          ;
          break;
        default:
          jj_la1[1] = jj_gen;
          break label_1;
        }
        procDef();
      }
      instrBlock();
      jj_consume_token(GORP);
                    try {
                        Thread.sleep(900);
                } catch (InterruptedException e) {
                        System.err.format("IOException: %s%n", e);
                }
                        sistema.printOutput(salida);
                        {if (true) return true;}
      break;
    case 0:
      jj_consume_token(0);
                 {if (true) return false;}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public void varDecl() throws ParseException {
    jj_consume_token(VAR);
    jj_consume_token(NAME);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 48:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      jj_consume_token(48);
      jj_consume_token(NAME);
    }
    jj_consume_token(49);
                        /*TODO : Lógica Java declaración de variables*/
                        salida="Variable declaration";
  }

  final public void procDef() throws ParseException {
    jj_consume_token(PROC);
    jj_consume_token(NAME);
    jj_consume_token(50);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NAME:
      params();
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    jj_consume_token(51);
    instrBlock();
    jj_consume_token(CORP);
                        /*TODO : Lógica Java definición de procedimientos*/
                        salida="Procedure definition";
  }

  final public void params() throws ParseException {
    jj_consume_token(NAME);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 48:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      jj_consume_token(48);
      jj_consume_token(NAME);
    }
  }

  final public void instrBlock() throws ParseException {
    jj_consume_token(52);
    instr();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 49:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_4;
      }
      jj_consume_token(49);
      instr();
    }
    jj_consume_token(53);
  }

  final public void instr() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STEP:
    case JUMP:
    case JUMPTO:
    case VEER:
    case LOOK:
    case DROP:
    case GRAB:
    case GET:
    case FREE:
    case POP:
    case DMOVE:
    case OMOVE:
    case NAME:
      command();
      break;
    case IF:
    case WHILE:
    case REPEATTIMES:
      ctrlStruct();
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void command() throws ParseException {
                int n, m;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NAME:
      jj_consume_token(NAME);
      jj_consume_token(54);
      jj_consume_token(NUM);
                                       /*TODO: Lógica asignación*/salida = "Command: Variable assignment";
      break;
    case STEP:
      jj_consume_token(STEP);
      jj_consume_token(50);
      n = num();
      jj_consume_token(51);
                                           world.moveForward(n, false); salida = "Command: Move steps forward ";
      break;
    case JUMP:
      jj_consume_token(JUMP);
      jj_consume_token(50);
      n = num();
      jj_consume_token(51);
                                           world.moveForward(n, true); salida = "Command: Jump steps forward ";
      break;
    case JUMPTO:
      jj_consume_token(JUMPTO);
      jj_consume_token(50);
      n = num();
      jj_consume_token(48);
      m = num();
      jj_consume_token(51);
                                                        world.setPostion(n,m); salida = "Command: Jump to position ";
      break;
    case VEER:
      jj_consume_token(VEER);
      jj_consume_token(50);
      veer();
      jj_consume_token(51);
      break;
    case LOOK:
      jj_consume_token(LOOK);
      jj_consume_token(50);
      look();
      jj_consume_token(51);
      break;
    case DROP:
      jj_consume_token(DROP);
      jj_consume_token(50);
      n = num();
      jj_consume_token(51);
                                           world.putChips(n); salida = "Command: Drop chips from its position ";
      break;
    case GRAB:
      jj_consume_token(GRAB);
      jj_consume_token(50);
      n = num();
      jj_consume_token(51);
                                           world.grabBalloons(n); salida = "Command: Grab balloons from its position ";
      break;
    case GET:
      jj_consume_token(GET);
      jj_consume_token(50);
      n = num();
      jj_consume_token(51);
                                          world.pickChips(n); salida = "Command: Get chips from its position ";
      break;
    case FREE:
      jj_consume_token(FREE);
      jj_consume_token(50);
      n = num();
      jj_consume_token(51);
                                           world.putBalloons(n); salida = "Command: Put balloons from its position ";
      break;
    case POP:
      jj_consume_token(POP);
      jj_consume_token(50);
      n = num();
      jj_consume_token(51);
                                          world.popBalloons(n); salida = "Command: Pop balloons from its position ";
      break;
    case DMOVE:
      jj_consume_token(DMOVE);
      jj_consume_token(50);
      dMove();
      jj_consume_token(51);
      break;
    case OMOVE:
      jj_consume_token(OMOVE);
      jj_consume_token(50);
      oMove();
      jj_consume_token(51);
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void ctrlStruct() throws ParseException {
                boolean bool = false;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IF:
      ifElseFi();
      break;
      ifFi();
      break;
    case WHILE:
      whileDoOd();
      break;
    case REPEATTIMES:
      repeatTimes();
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void ifElseFi() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(50);
    bool = condition();
    jj_consume_token(51);
    instrBlock();
    jj_consume_token(ELSE);
    instrBlock();
    jj_consume_token(FI);
                        /*TODO : Lógica Java if else*/
                        salida="'if-else' conditional control structure";
  }

  final public void ifFi() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(50);
    bool = condition();
    jj_consume_token(51);
    instrBlock();
    jj_consume_token(FI);
                        /*TODO : Lógica Java if */
                        salida="'if' conditional control structure";
  }

  final public void whileDoOd() throws ParseException {
    jj_consume_token(WHILE);
    jj_consume_token(50);
    bool = condition();
    jj_consume_token(51);
    jj_consume_token(DO);
    instrBlock();
    jj_consume_token(OD);
                        /*TODO : Lógica Java while */
                        salida="'while' loop control structure";
  }

  final public void repeatTimes() throws ParseException {
                int n;
    jj_consume_token(REPEATTIMES);
    n = num();
    instrBlock();
    jj_consume_token(PER);
                        /*TODO : Lógica repeatTimes */
                        salida="'repeatTimes' loop control structure";
  }

  final public void condition() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ISFACING:
      jj_consume_token(ISFACING);
      jj_consume_token(50);
      bool = isFacing();
      jj_consume_token(51);
      break;
    case ISVALID:
      jj_consume_token(ISVALID);
      jj_consume_token(50);
      bool = isValid();
      jj_consume_token(51);
      break;
    case CANMOVE:
      jj_consume_token(CANMOVE);
      jj_consume_token(50);
      bool = canMove();
      jj_consume_token(51);
      break;
    case NOT:
      jj_consume_token(NOT);
      jj_consume_token(50);
      bool = not();
      jj_consume_token(51);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//	void procCall():
//	{
//	}
//	{
//		// TODO
//	}
  final public void veer() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case RIGHT:
      jj_consume_token(RIGHT);
                            world.turnRight(); salida = "Command: Veer right ";
      break;
    case AROUND:
      jj_consume_token(AROUND);
                             world.turnRight(); world.turnRight(); salida = "Command: Veer around ";
      break;
    case LEFT:
      jj_consume_token(LEFT);
                           world.turnRight(); world.turnRight(); world.turnRight(); salida = "Command: Veer left ";
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void look() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NORTH:
      jj_consume_token(NORTH);
                        if (world.facingWest()) {
                                world.turnRight();
                        } else if (world.facingSouth()) {
                                world.turnRight(); world.turnRight();
                        } else if (world.facingEast()) {
                                world.turnRight(); world.turnRight(); world.turnRight();
                        }
                        salida = "Command: Look north ";
      break;
    case SOUTH:
      jj_consume_token(SOUTH);
                        if (world.facingEast()) {
                                world.turnRight();
                        } else if (world.facingNorth()) {
                                world.turnRight(); world.turnRight();
                        } else if (world.facingWest()) {
                                world.turnRight(); world.turnRight(); world.turnRight();
                        }
                        salida = "Command: Look south ";
      break;
    case EAST:
      jj_consume_token(EAST);
                        if (world.facingNorth()) {
                                world.turnRight();
                        } else if (world.facingWest()) {
                                world.turnRight(); world.turnRight();
                        } else if (world.facingSouth()) {
                                world.turnRight(); world.turnRight(); world.turnRight();
                        }
                        salida = "Command: Look east ";
      break;
    case WEST:
      jj_consume_token(WEST);
                        if (world.facingSouth()) {
                                world.turnRight();
                        } else if (world.facingEast()) {
                                world.turnRight(); world.turnRight();
                        } else if (world.facingNorth()) {
                                world.turnRight(); world.turnRight(); world.turnRight();
                        }
                        salida = "Command: Look west ";
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void dMove() throws ParseException {
                int n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
      n = num();
      jj_consume_token(48);
      jj_consume_token(FRONT);
                                            world.moveForward(n, false);salida = "Command: Move to the front, face original direction ";
      break;
    case RIGHT:
      jj_consume_token(RIGHT);
                        world.turnRight();
                        world.moveForward(n, false);
                        world.turnRight(); world.turnRight(); world.turnRight();
                        salida = "Command: Move to the right, face original direction ";
      break;
    case BACK:
      jj_consume_token(BACK);
                        world.turnRight(); world.turnRight();
                        world.moveForward(n, false);
                        world.turnRight(); world.turnRight();
                        salida = "Command: Move to the back, face original direction ";
      break;
    case LEFT:
      jj_consume_token(LEFT);
                        world.turnRight(); world.turnRight(); world.turnRight();
                        world.moveForward(n, false);
                        world.turnRight();
                        salida = "Command: Move to the left, face original direction ";
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void oMove() throws ParseException {
                int n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
      num();
      jj_consume_token(48);
      jj_consume_token(NORTH);
                        if (world.facingWest()) {
                                world.turnRight();
                        } else if (world.facingSouth()) {
                                world.turnRight(); world.turnRight();
                        } else if (world.facingEast()) {
                                world.turnRight(); world.turnRight(); world.turnRight();
                        }
                        world.moveForward(n, false);
                        salida = "Command: Face north, move steps";
      break;
    case SOUTH:
      jj_consume_token(SOUTH);
                        if (world.facingEast()) {
                                world.turnRight();
                        } else if (world.facingNorth()) {
                                world.turnRight(); world.turnRight();
                        } else if (world.facingWest()) {
                                world.turnRight(); world.turnRight(); world.turnRight();
                        }
                        world.moveForward(n, false);
                        salida = "Command: Face south, move steps";
      break;
    case EAST:
      jj_consume_token(EAST);
                        if (world.facingNorth()) {
                                world.turnRight();
                        } else if (world.facingWest()) {
                                world.turnRight(); world.turnRight();
                        } else if (world.facingSouth()) {
                                world.turnRight(); world.turnRight(); world.turnRight();
                        }
                        world.moveForward(n, false);
                        salida = "Command: Face east, move steps";
      break;
    case WEST:
      jj_consume_token(WEST);
                        if (world.facingSouth()) {
                                world.turnRight();
                        } else if (world.facingEast()) {
                                world.turnRight(); world.turnRight();
                        } else if (world.facingNorth()) {
                                world.turnRight(); world.turnRight(); world.turnRight();
                        }
                        world.moveForward(n, false);
                        salida = "Command: Face west, move steps";
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void isFacing() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NORTH:
      jj_consume_token(NORTH);
                        bool=world.facingNorth();
      break;
    case SOUTH:
      jj_consume_token(SOUTH);
                        bool=world.facingSouth();
      break;
    case EAST:
      jj_consume_token(EAST);
                        bool=world.facingEast();
      break;
    case WEST:
      jj_consume_token(WEST);
                        bool=world.facingWest();
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void isValid() throws ParseException {
                int n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STEP:
      jj_consume_token(STEP);
      jj_consume_token(48);
      n = num();

      break;
    case JUMP:
      jj_consume_token(JUMP);
      jj_consume_token(48);
      n = num();

      break;
    case GRAB:
      jj_consume_token(GRAB);
      jj_consume_token(48);
      n = num();

      break;
    case POP:
      jj_consume_token(POP);
      jj_consume_token(48);
      n = num();

      break;
    case PICK:
      jj_consume_token(PICK);
      jj_consume_token(48);
      n = num();

      break;
    case FREE:
      jj_consume_token(FREE);
      jj_consume_token(48);
      n = num();

      break;
    case DROP:
      jj_consume_token(DROP);
      jj_consume_token(48);
      n = num();

      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

        /*
	|	< STEP >  "(" n=num() ")" {world.moveForward(n, false); salida = "Command: Move steps forward ";}  
	|	< JUMP >  "(" n=num() ")" {world.moveForward(n, true); salida = "Command: Jump steps forward ";}
	|	< JUMPTO >  "(" n=num() "," m=num()")" {world.setPostion(n,m); salida = "Command: Jump to position ";}
	|	< VEER >  "(" veer() ")"
	|	< LOOK >  "(" look() ")"
	|	< DROP >  "(" n=num() ")" {world.putChips(n); salida = "Command: Drop chips from its position ";}
	|	< GRAB >  "(" n=num() ")" {world.grabBalloons(n); salida = "Command: Grab balloons from its position ";}
	|	< GET >  "(" n=num() ")" {world.pickChips(n); salida = "Command: Get chips from its position ";}
	|	< FREE >  "(" n=num() ")" {world.putBalloons(n); salida = "Command: Put balloons from its position ";}
	|	< POP >  "(" n=num() ")" {world.popBalloons(n); salida = "Command: Pop balloons from its position ";}
	|	< DMOVE >  "(" dMove() ")"
	|	< OMOVE >  "(" oMove() ")"
	*/
  final public void canMove() throws ParseException {
                int n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NORTH:
      jj_consume_token(NORTH);
      jj_consume_token(48);
      n = num();

      break;
    case SOUTH:
      jj_consume_token(SOUTH);
      jj_consume_token(48);
      n = num();

      break;
    case EAST:
      jj_consume_token(EAST);
      jj_consume_token(48);
      n = num();

      break;
    case WEST:
      jj_consume_token(WEST);
      jj_consume_token(48);
      n = num();

      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void not() throws ParseException {
    bool = condition();
                                  bool = !bool;
  }

        /**
	 * Unsigned decimal number
	 * @return the corresponding value of the string
	 * @error  corresponding value is too large
	 */
  final public int num() throws ParseException, Error {
                int total=1;
    jj_consume_token(NUM);
                        try
                        {
                                total = Integer.parseInt(token.image);
                        }
                        catch (NumberFormatException ee)
                        {
                                {if (true) throw new Error("Number out of bounds: "+token.image+" !!");}
                        }
                        {if (true) return total;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public RobotTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[18];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x1,0x0,0x0,0x0,0x0,0x93ffe0,0x1ffe0,0x920000,0x1e000000,0x0,0xc0000000,0x0,0x80000000,0xc0000000,0x20006c60,0xc0000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x200,0x400,0x80,0x10000,0x1000,0x10000,0x20000,0x1000,0x1000,0x0,0x0,0x1c,0x3,0x4058,0x4003,0x3,0x0,0x3,};
   }

  /** Constructor with InputStream. */
  public Robot(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Robot(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new RobotTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Robot(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new RobotTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Robot(RobotTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(RobotTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[55];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 18; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 55; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
