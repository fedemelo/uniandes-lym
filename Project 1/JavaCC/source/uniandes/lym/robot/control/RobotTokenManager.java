/* Generated By:JavaCC: Do not edit this line. RobotTokenManager.java */
package uniandes.lym.robot.control;
import uniandes.lym.robot.kernel.*;
import uniandes.lym.robot.view.Console;
import java.awt.Point;
import java.io.*;
import java.util.Vector;
import java.util.LinkedList;

/** Token Manager. */
public class RobotTokenManager implements RobotConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0xfffffffffe0L) != 0L)
         {
            jjmatchedKind = 44;
            return 1;
         }
         return -1;
      case 1:
         if ((active0 & 0x660000L) != 0L)
            return 1;
         if ((active0 & 0xfffff99ffe0L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 1;
            return 1;
         }
         return -1;
      case 2:
         if ((active0 & 0x20011005000L) != 0L)
            return 1;
         if ((active0 & 0xdffee99afe0L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 2;
            return 1;
         }
         return -1;
      case 3:
         if ((active0 & 0xdd320082fe0L) != 0L)
            return 1;
         if ((active0 & 0x2cce918000L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 44;
               jjmatchedPos = 3;
            }
            return 1;
         }
         return -1;
      case 4:
         if ((active0 & 0x28c0118000L) != 0L)
            return 1;
         if ((active0 & 0x40e800080L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 4;
            return 1;
         }
         return -1;
      case 5:
         if ((active0 & 0x400000080L) != 0L)
            return 1;
         if ((active0 & 0xe800000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 5;
            return 1;
         }
         return -1;
      case 6:
         if ((active0 & 0xc000000L) != 0L)
            return 1;
         if ((active0 & 0x2800000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 6;
            return 1;
         }
         return -1;
      case 7:
         if ((active0 & 0x800000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 7;
            return 1;
         }
         if ((active0 & 0x2000000L) != 0L)
            return 1;
         return -1;
      case 8:
         if ((active0 & 0x800000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 8;
            return 1;
         }
         return -1;
      case 9:
         if ((active0 & 0x800000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 9;
            return 1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 40:
         return jjStopAtPos(0, 50);
      case 41:
         return jjStopAtPos(0, 51);
      case 44:
         return jjStopAtPos(0, 48);
      case 58:
         return jjMoveStringLiteralDfa1_0(0x40000000000000L);
      case 59:
         return jjStopAtPos(0, 49);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa1_0(0x4000000000L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa1_0(0x80008000000L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa1_0(0x208400L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa1_0(0x100080000L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa1_0(0x2000042000L);
      case 71:
      case 103:
         return jjMoveStringLiteralDfa1_0(0x10000001800L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa1_0(0x6020000L);
      case 74:
      case 106:
         return jjMoveStringLiteralDfa1_0(0xc0L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa1_0(0x1000000200L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa1_0(0x50000000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa1_0(0x410000L);
      case 80:
      case 112:
         return jjMoveStringLiteralDfa1_0(0x48021004000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa1_0(0x800800000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa1_0(0x80000020L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa1_0(0x20000000100L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa1_0(0x200100000L);
      case 123:
         return jjStopAtPos(0, 52);
      case 125:
         return jjStopAtPos(0, 53);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 61:
         if ((active0 & 0x40000000000000L) != 0L)
            return jjStopAtPos(1, 54);
         break;
      case 65:
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x24108000000L);
      case 68:
      case 100:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(1, 22, 1);
         break;
      case 69:
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x1201801100L);
      case 70:
      case 102:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(1, 17, 1);
         break;
      case 72:
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
      case 73:
      case 105:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(1, 18, 1);
         return jjMoveStringLiteralDfa2_0(active0, 0x820000000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x18000L);
      case 79:
      case 111:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(1, 21, 1);
         return jjMoveStringLiteralDfa2_0(active0, 0x900d0004200L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x4a400002c00L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa2_0(active0, 0x6000000L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0xc0L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x4020000000L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x2120L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x1002000000L);
      case 71:
      case 103:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0xc0L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x4a400018600L);
      case 80:
      case 112:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(2, 14, 1);
         return jjMoveStringLiteralDfa3_0(active0, 0x800000L);
      case 82:
      case 114:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(2, 24, 1);
         else if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 41, 1);
         return jjMoveStringLiteralDfa3_0(active0, 0x90040000000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x300080000L);
      case 84:
      case 116:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(2, 12, 1);
         else if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(2, 28, 1);
         break;
      case 85:
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x6000000L);
      case 66:
      case 98:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(3, 11, 1);
         break;
      case 67:
      case 99:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 42, 1);
         break;
      case 69:
      case 101:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(3, 13, 1);
         else if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(3, 19, 1);
         return jjMoveStringLiteralDfa4_0(active0, 0x800000L);
      case 71:
      case 103:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 39, 1);
         break;
      case 72:
      case 104:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L);
      case 75:
      case 107:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(3, 9, 1);
         else if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(3, 29, 1);
         else if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 38, 1);
         break;
      case 76:
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000L);
      case 80:
      case 112:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(3, 5, 1);
         else if ((active0 & 0x40L) != 0L)
         {
            jjmatchedKind = 6;
            jjmatchedPos = 3;
         }
         else if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(3, 10, 1);
         else if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 40, 1);
         else if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 43, 1);
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      case 82:
      case 114:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(3, 8, 1);
         break;
      case 84:
      case 116:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(3, 32, 1);
         else if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(3, 33, 1);
         else if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 36, 1);
         return jjMoveStringLiteralDfa4_0(active0, 0xc0000000L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa4_0(active0, 0x18000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000L);
      case 69:
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(4, 15, 1);
         else if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(4, 16, 1);
         else if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(4, 20, 1);
         break;
      case 72:
      case 104:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(4, 30, 1);
         else if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(4, 31, 1);
         break;
      case 76:
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x400000000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000000L);
      case 84:
      case 116:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(4, 35, 1);
         else if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 37, 1);
         return jjMoveStringLiteralDfa5_0(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 68:
      case 100:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(5, 34, 1);
         break;
      case 73:
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x6000000L);
      case 79:
      case 111:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(5, 7, 1);
         break;
      case 84:
      case 116:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 68:
      case 100:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(6, 26, 1);
         break;
      case 69:
      case 101:
         if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(6, 27, 1);
         break;
      case 78:
      case 110:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000000L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa7_0(active0, 0x800000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 71:
      case 103:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(7, 25, 1);
         break;
      case 73:
      case 105:
         return jjMoveStringLiteralDfa8_0(active0, 0x800000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 77:
      case 109:
         return jjMoveStringLiteralDfa9_0(active0, 0x800000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 69:
      case 101:
         return jjMoveStringLiteralDfa10_0(active0, 0x800000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 83:
      case 115:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(10, 23, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 3;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  jjCheckNAdd(2);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 1:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  jjCheckNAdd(1);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, "\54", "\73", "\50", "\51", "\173", 
"\175", "\72\75", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x7f5fffffffffe1L, 
};
static final long[] jjtoSkip = {
   0x1eL, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[3];
private final int[] jjstateSet = new int[6];
protected char curChar;
/** Constructor. */
public RobotTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public RobotTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 3; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
