EXPERIMENT NO : 01
Title: Design suitable Data structures and implement Pass-I and Pass-II of a two-pass assembler for pseudo-machine. Implementation should consist of a few instructions from each category and few assembler directives. The output of Pass-I (intermediate code file and symbol table) should be input for Pass-II.
Program:

      import java.io.*;
class Main
{
    public static void main(String args[])throws Exception
    {  
    
       // FileReader FP=new FileReader(args[0]);
        FileReader FP=new FileReader(C:\Users\Admin\Desktop\VAISHALI\programs and output\Pass1\Input.txt);
    
        BufferedReader bufferedReader = new BufferedReader(FP);     
        
        String line=null;
        String line2=null;
    
        int line_count=0,LC=0,LC1=0,symTabLine=0,opTabLine=0,litTabLine=0,poolTabLine=0,MachineTabLine=0;
          
         //Data Structures
         final int MAX=100;
         String SymbolTab[][]=new String[MAX][3];
         String OpTab[][]=new String[MAX][3];
         String LitTab[][]=new String[MAX][2];
         int PoolTab[]=new int[MAX];
         String Machine[][] = new String[MAX][4];
         int litTabAddress=0;
/*---------------------------------------------------------------------------------------------------*/
         
         System.out.println("___________________________________________________");
            while((line = bufferedReader.readLine()) != null)
             {
              //   String[] tokens = line.split("\t");
                 String tokens[] = line.split("[\t ]+");
             
                if(line_count==0)
                {
                    LC=Integer.parseInt(tokens[2]); 
                    LC=LC-1;
                    //set LC to operand of START
                    for(int i=0;i<tokens.length;i++)        //for printing the input program
                        System.out.print(tokens[i]+"\t");
                    System.out.println("");
                }
                else
                {
                         if(tokens[1].equalsIgnoreCase("START")||tokens[1].equalsIgnoreCase("END")||tokens[1].equalsIgnoreCase("ORIGIN")||tokens[1].equalsIgnoreCase("EQU")||tokens[1].equalsIgnoreCase("LTORG"))       //if Assembler Directive
                    {     
                      
                      LC = LC-1;
                      //Because we don't count assembler directives
                    }
                     for(int i=0;i<tokens.length;i++) //for printing the input program
                        System.out.print(tokens[i]+"\t");
                     System.out.println("");
                    if(!tokens[0].equals(""))
                    {
             
                        //Inserting into Symbol Table
                        SymbolTab[symTabLine][0]=tokens[0];
                        SymbolTab[symTabLine][1]=Integer.toString(LC);
                        SymbolTab[symTabLine][2]=Integer.toString(1);
                        symTabLine++;
                    }
                else if(tokens[1].equalsIgnoreCase("DS")||tokens[1].equalsIgnoreCase("DC"))
                {
                    //Entry into symbol table for declarative statements
                    SymbolTab[symTabLine][0]=tokens[0];
                    SymbolTab[symTabLine][1]=Integer.toString(LC);
                    SymbolTab[symTabLine][2]=Integer.toString(1);
                        symTabLine++;
                }
                
                //Entry of literals into literal table
                if(tokens.length>=2 && tokens[1].charAt(0)=='=')
                {
                    
                    LitTab[litTabLine][0]=tokens[1];
                        LitTab[litTabLine][1]=Integer.toString(LC);
                        litTabLine++;
                 
                    
                }
                
                
                else if(tokens[1]!=null)
                {   
                    
                    
                        //Entry of Mnemonic in opcode table
                    OpTab[opTabLine][0]=tokens[1];
                    
                    if(tokens[1].equalsIgnoreCase("START")||tokens[1].equalsIgnoreCase("END")||tokens[1].equalsIgnoreCase("ORIGIN")||tokens[1].equalsIgnoreCase("EQU")||tokens[1].equalsIgnoreCase("LTORG"))        //if Assembler Directive
                    {      
                            //Opems Opcode File
                            FileReader FP2=new FileReader("opcode.txt");
                            BufferedReader bufferedReader1 = new BufferedReader(FP2);
                            OpTab[opTabLine][1]="AD";
                            while((line2 = bufferedReader1.readLine()) != null)
                            {  
                              String a = tokens[1];
                              String OPS[] = line2.split("[\t ]+");
                              String b=OPS[0];
                     
                              if(b.equalsIgnoreCase(a))
                              {
                                   
                                   OpTab[opTabLine][2]=OPS[1];  
                                   break;
                              }
                             
                            }
                            bufferedReader1.close();
                                
                    }                   
                    else if(tokens[1].equalsIgnoreCase("DS")||tokens[1].equalsIgnoreCase("DC"))
                    {
                        OpTab[opTabLine][1]="DL";
                        OpTab[opTabLine][2]="R#7";                  
                    }
                    else
                    {   
                         //Opens Opcode File
                            FileReader FP2=new FileReader("opcode.txt");
                            BufferedReader bufferedReader1 = new BufferedReader(FP2);
                            OpTab[opTabLine][1]="IS";
                            while((line2 = bufferedReader1.readLine()) != null)
                           {  
                              String a = tokens[1];
                              String OPS[] = line2.split("[\t ]+");
                              String b=OPS[0];
                     
                              if(b.equalsIgnoreCase(a))
                              {
                                   
                               
                                   OpTab[opTabLine][2]="("+OPS[1]+",1)";    
                                   break;
                              }
                             
                           }  
                           bufferedReader1.close();
                        
                        
                    }
                    opTabLine++;
                }
                }
                line_count++;
                LC++;
            }   

            System.out.println("___________________________________________________");  

            //print symbol table
            System.out.println("\n\n    SYMBOL TABLE        ");
            System.out.println("--------------------------");           
            System.out.println("SYMBOL\tADDRESS\tLENGTH");
            System.out.println("--------------------------");           
            for(int i=0;i<symTabLine;i++)
                System.out.println(SymbolTab[i][0]+"\t"+SymbolTab[i][1]+"\t"+SymbolTab[i][2]);
            System.out.println("--------------------------");

            //print opcode table
            System.out.println("\n\n    OPCODE TABLE        ");
            System.out.println("----------------------------");         
            System.out.println("MNEMONIC\tCLASS\tINFO");
            System.out.println("----------------------------");         
            for(int i=0;i<opTabLine;i++)
                System.out.println(OpTab[i][0]+"\t\t"+OpTab[i][1]+"\t"+OpTab[i][2]);
            System.out.println("----------------------------");

            //print literal table
            System.out.println("\n\n   LITERAL TABLE        ");
            System.out.println("-----------------");            
            System.out.println("LITERAL\tADDRESS");
            System.out.println("-----------------");            
            for(int i=0;i<litTabLine;i++)
                System.out.println(LitTab[i][0]+"\t"+LitTab[i][1]);
            System.out.println("------------------");
    

            //intialization of POOLTAB
            for(int i=0;i<litTabLine;i++)
            {
                if(LitTab[i][0]!=null && LitTab[i+1][0]!=null ) //if literals are present
                {
                    if(i==0)
                    {
                        PoolTab[poolTabLine]=i;
                        poolTabLine++;
                    }
                    else if(Integer.parseInt(LitTab[i][1])<(Integer.parseInt(LitTab[i+1][1]))-1)
                    {   
                        PoolTab[poolTabLine]=i+1;
                        poolTabLine++;
                    }
                }
            }
            //print pool table
            System.out.println("\n\n   POOL TABLE       ");
            System.out.println("-----------------");            
            System.out.println("LITERAL NUMBER");
            System.out.println("-----------------");            
            for(int i=0;i<poolTabLine;i++)
                System.out.println(PoolTab[i]);
            System.out.println("------------------");
            
        
            
            // Always close files.
            bufferedReader.close();
            
            //Machine CODE Generation Pass2
            System.out.println("___________________________________________________");
            line_count=0;
            FileReader FP6=new FileReader("Input.txt");
            BufferedReader bufferedReader3 = new BufferedReader(FP6);
            while((line = bufferedReader3.readLine()) != null)
             {
                 String tokens1[] = line.split("[\t ]+");
                if(line_count==0)
                {
                    LC1=Integer.parseInt(tokens1[2]);   
                    LC1=LC1-1;
                }
                else if(tokens1[1]!=null)
                {
                    if(tokens1[1].equalsIgnoreCase("END")||tokens1[1].equalsIgnoreCase("ORIGIN")||tokens1[1].equalsIgnoreCase("EQU")||tokens1[1].equalsIgnoreCase("LTORG")||tokens1[1].equalsIgnoreCase("DS")||tokens1[1].equalsIgnoreCase("DC"))       //if Assembler Directive
                    {
                       Machine[MachineTabLine][0]=Integer.toString(LC1);
                        Machine[MachineTabLine][1]=" ";
                         Machine[MachineTabLine][2]=" ";
                          Machine[MachineTabLine][3]=" ";
                    }
                    else if(!tokens1[1].equalsIgnoreCase("START"))
                    {
                    
                      FileReader FP7=new FileReader("opcode.txt");
                      BufferedReader bufferedReader4 = new BufferedReader(FP7);
                       Machine[MachineTabLine][0]=Integer.toString(LC1);
                       while((line2 = bufferedReader4.readLine()) != null)
                            {  
                              String a = tokens1[1];
                              String OPS[] = line2.split("[\t ]+");
                              String b=OPS[0];
                     
                              if(b.equalsIgnoreCase(a))
                              {
                                   Machine[MachineTabLine][1]=OPS[1];   
                                   break;
                              }
                     }
                     bufferedReader4.close();//Closing File
                     if(tokens1.length>2)
                     {
                      FileReader FP11=new FileReader("opcode.txt");
                      BufferedReader bufferedReader5 = new BufferedReader(FP11);
                      Machine[MachineTabLine][0]=Integer.toString(LC1);
                      while((line2 = bufferedReader5.readLine()) != null)
                      {         
                              //For Registers
                              String OPS[] = line2.split("[\t ]+");
                              String e=OPS[0];
                              String c= tokens1[2];
                            if(c.length()>1)
                            {
                                    String d = c.substring(0,4);
                                  if(e.equalsIgnoreCase(d)||tokens1[2].equalsIgnoreCase(d)||tokens1[2].equalsIgnoreCase("CREG,"))
                                   {
                                     Machine[MachineTabLine][2]=OPS[1];
                                   }    
                            }
                                //For symbols
                                char f = c.charAt(0);
                                String j =Character.toString(f);
                                for(int i=0;i<symTabLine;i++)
                                {
                                 if((j.equalsIgnoreCase(SymbolTab[i][0])))
                                 {   
                                      if(tokens1.length==3)
                                      {
                                          Machine[MachineTabLine][2]="0";
                                          Machine[MachineTabLine][3]=SymbolTab[i][1];
                                      }   
                                 }
                            }    
                        }      
                     bufferedReader5.close();         
                    }
                    if(tokens1.length>3)
                      {
                            String d =tokens1[3];
                            for(int i=0;i<symTabLine;i++)
                            {
                                 if((d.equalsIgnoreCase(SymbolTab[i][0])))
                                 {
                                      Machine[MachineTabLine][3]=SymbolTab[i][1];
                                 }
                            }
                       }
                            
            
                    }
                 
                     if(tokens1.length>=2 && tokens1[1].charAt(0)=='=')
                    {
                        char lits =tokens1[1].charAt(2);
                        Machine[MachineTabLine][0]=Integer.toString(LC1);
                        Machine[MachineTabLine][1]="00";
                        Machine[MachineTabLine][2]="0";
                        Machine[MachineTabLine][3]="00"+lits;
                    }
                    MachineTabLine++;
                }
                line_count+=1;
                LC1+=1;
                }
            System.out.println("\n\n   MACHINE CODE         ");
             System.out.println("-----------------");           
                        
            for(int i=0;i<MachineTabLine;i++)
                System.out.println(Machine[i][0]+"\t"+Machine[i][1]+"\t"+Machine[i][2]+"\t"+Machine[i][3]);
            System.out.println("------------------");
                bufferedReader3.close();
             }
    }


Input:
    START	100	
	READ	A
	READ    B
LABLE	MOVER	AREG,   A
    ADD    AREG,    B 
	LTORG	
        ='5'	
		='1'	
	    ='6'
	MOVEM	AREG,    C	
	LTORG	
		='2'	
    PRINT    C	
A	DS	1	
B	DS	1
C	DS	1
	END
OPCODE:
START  R#1
END    R#2
ORIGIN R#3
EQU    R#4
LTORG  R#5
DC     R#7
STOP  00
ADD   01
SUB   02
MULT  03
MOVER 04
MOVEM 05
COMP  06
BC    07
DIV   08
READ  09
PRINT 10
JUMP  11
AREG  1
BREG  1
CREG  1


Output:
Microsoft Windows [Version 10.0.19042.1288]
(c) Microsoft Corporation. All rights reserved.

C:\Users\User>java -version
java version "17.0.2" 2022-01-18 LTS
Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)

C:\Users\User>cd \

C:\>cd SPOS

C:\SPOS>cd Pass1

C:\SPOS\Pass1>javac Main.java

C:\SPOS\Pass1>java Main Input.txt
___________________________________________________
        START   100
        READ    A
        READ    B
LABLE   MOVER   AREG,   A
        ADD     AREG,   B
        LTORG
        ='5'
        ='1'
        ='6'
        MOVEM   AREG,   C
        LTORG
        ='2'
        PRINT   C
A       DS      1
B       DS      1
C       DS      1
        END
___________________________________________________


        SYMBOL TABLE
--------------------------
SYMBOL  ADDRESS LENGTH
--------------------------
LABLE   102     1
A       110     1
B       111     1
C       112     1
--------------------------


        OPCODE TABLE
----------------------------
MNEMONIC        CLASS   INFO
----------------------------
READ            IS      (09,1)
READ            IS      (09,1)
MOVER           IS      (04,1)
ADD             IS      (01,1)
LTORG           AD      R#5
MOVEM           IS      (05,1)
LTORG           AD      R#5
PRINT           IS      (10,1)
DS              DL      R#7
DS              DL      R#7
DS              DL      R#7
END             AD      R#2
----------------------------


   LITERAL TABLE
-----------------
LITERAL ADDRESS
-----------------
='5'    104
='1'    105
='6'    106
='2'    108
------------------


   POOL TABLE
-----------------
LITERAL NUMBER
-----------------
0
3
------------------
___________________________________________________


   MACHINE CODE
-----------------
100     09      0       110
101     09      0       111
102     04      1       110
103     01      1       111
104
105     00      0       005
106     00      0       001
107     00      0       006
108     05      1       112
109
110     00      0       002
111     10      0       112
112
113
114
115
------------------

C:\SPOS\Pass1>

EXPERIMENT NO : 02
Title: Design suitable data structures and implement Pass-I and Pass-II of a two-pass macroprocessor. The output of Pass-I (MNT, MDT and intermediate code file without any macro definitions) should be input for Pass-II.
Program:
import java.util.*;
import java.io.*;
class Main
{
    static String mnt[][]=new String[6][3]; //assuming 5 macros in 1 program
    static String ala[][]=new String[10][2]; //assuming 2 arguments in each macro
     static String alax[][]=new String[10][3];
    static String mdt[][]=new String[20][1]; //assuming 4 LOC for each macro
    static int mntc=0,mdtc=0,alac=0,cnt=0;
    
    public static void main(String args[])
    {    
        pass1();
        System.out.println("\n*********PASS-1 MACROPROCESSOR***********\n");
        System.out.println("MACRO NAME TABLE (MNT)\n");
        System.out.println("Sr. Macro  Loc\n");
        display(mnt,mntc,3);
        System.out.println("\n");
        System.out.println("ARGUMENT LIST ARRAY(ALA) for Pass1\n");
        display(alax,cnt,3);
        System.out.println("\n");
        System.out.println("MACRO DEFINITION TABLE (MDT)\n");
        System.out.println("Sr. \t MDT");
        display(mdt,mdtc,1);
        System.out.println("\n");
    }

    static void pass1()
    {
        int index=0,i;
        String s,prev="",substring;
        try
        {
            BufferedReader inp = new BufferedReader(new FileReader("input.txt"));
            File op = new File("pass1_output.txt");
            if (!op.exists())
            op.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(op.getAbsoluteFile()));
            while((s=inp.readLine())!=null)
            {
                if(s.equalsIgnoreCase("MACRO"))
                {   int flag=0;//FLAG PLAYS A MAJOR ROLE IN SELECTING MACRO
                    prev=s;
                    for(prev=s;!(s=inp.readLine()).equalsIgnoreCase("MEND");mdtc++)
                    {   
                        if(prev.equalsIgnoreCase("MACRO"))
                        {   
                            
                            StringTokenizer st=new StringTokenizer(s);
                            String str[]=new String[st.countTokens()];
                            for(i=0;i<str.length;i++)
                            str[i]=st.nextToken();
                            if(flag==0)
                            
                            {mnt[mntc][0]=(mntc+1)+" "; //mnt formation
                            mnt[mntc][1]=str[flag]+" ";
                            mnt[mntc++][2]=(mdtc)+" ";
                            
                            ALA(str[1],cnt);
                            /*cnt variable is used for counting macro 
                            for 1st macro it will take cnt=0
                            for 2nd macro it will take cnt=1
                            */
                            flag=1;
                            }
                        }
                        if(flag==1)                             //automatically eliminates tagging of arguments in definition
                        {  
                            
                            index=s.indexOf("&");               //finds index string starting with &
                            
                            substring=s.substring(index);       //extracts that substring
                        
                            if(!(substring.length()>11))         // length(CREG,&ARG4) ==11 and same for all other operations
                            {   
                                String tokens1[] = substring.split("[,]");      // tokens will be t1=&ARG1 t2=&ARG4
                            
                                for(i=0;i<tokens1.length;i++)                   // scans each element in alax with tokens1 element
                                {
                                     for(int m=0;m<3;m++)                       //here m is 3 bcoz alax's column size is 3 for better comparison in ALA table
                                       {
                                         if(alax[cnt][m].equalsIgnoreCase(tokens1[i]) )
                                         s=s.replaceAll(tokens1[i],"#"+m);
                                       }
                            
                                }  
                            }
                        }
                       
                        mdt[mdtc][0]=Integer.toString(mdtc)+"  "+s; 
                    }
                    mdt[mdtc++][0]=Integer.toString(mdtc-1)+"  "+s;
                    cnt+=1;  
                }
                else
                {
                 output.write(s);
                 output.newLine();
                }
            }
         output.close();
        }
        catch(FileNotFoundException ex)
        {
        System.out.println("UNABLE TO END FILE ");
        }
        catch(IOException e)
        {
        e.printStackTrace();
        }
    }
    static void ALA(String str,int count)
    
    {   int index=0;
        
        StringTokenizer st=new StringTokenizer(str,",");       //tokenizing the arguments or splitting them.
        String string[]=new String[st.countTokens()];
        
        for(int i=0;i<string.length;i++)
        {//ala table formation
            string[i]=st.nextToken();                         //inserts string tokens 
            index=string[i].indexOf("=");
            if(index!=-1)
            alax[count][i]=string[i].substring(0,index);     //enters the tokens into [arg1,arg2,arg3] format as column size of alax is 3
            
            else
            alax[count][i]=string[i]; 
        }  
        if(alax[count][2]==null)                             //if any column in a row is not filld then replace it with null
        alax[count][2]=" ";
    }
   
    static void display(String a[][],int n,int m)
    {
        int i,j;
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            if(a[i][j]==null)   //we dont want to show null in ALA
            {a[i][j]=" ";}
            else
            System.out.print(" "+a[i][j]+" ");
            System.out.println();
        }
    }
}   


INPUT: 
START
MACRO
INCR &ARG1,&ARG2,&BREG=K
ADD AREG,&ARG1
MOVER &BREG,&ARG2
MEND
MACRO
PVG &ARG3,&ARG4,&CREG=K
SUB AREG,&ARG3
MOVER &CREG,&ARG4
MEND
INCR
PVG  
DATA2
END

OUTPUT:
C:\SPOS\Pass2>javac Main.java

C:\SPOS\Pass2>java Main Input.txt

*********PASS-2 MACROPROCESSOR***********

MACRO NAME TABLE (MNT)

Sr. Macro  Loc

 1   INCR   0
 2   PVG   4


ARGUMENT LIST ARRAY(ALA) for Pass1

 &ARG1  &ARG2  &BREG
 &ARG3  &ARG4  &CREG


MACRO DEFINITION TABLE (MDT)

Sr.      MDT
 0  INCR &ARG1,&ARG2,&BREG=K
 1  ADD AREG,#0
 2  MOVER #2,#1
 3  MEND
 4  PVG &ARG3,&ARG4,&CREG=K
 5  SUB AREG,#0
 6  MOVER #2,#1
 7  MEND



C:\SPOS\Pass2>

EXPERIMENT NO : 03
Title: Write a program to simulate CPU Scheduling Algorithms: FCFS, SJF (Preemptive), Priority (NonPreemptive) and Round Robin (Preemptive).

Program:
1.FCFS Scheduling Algorithms:
import java.util.Scanner;;

public class FCFS {
    int Count=0;
    int[][] processData;
    Scanner scan = new Scanner(System.in);
    public void scheduleFCFS(){
        processData[0][2] = 0;
        processData[0][3] = processData[0][1];
        for(int i=1;i<Count;i++){
            processData[i][2] = processData[i-1][3];
            processData[i][3] = processData[i][1]+processData[i][2];
        }
    }
    public void setValue(){
        System.out.print("Enter Number Of processes : ");
        Count = scan.nextInt();
        processData = new int[Count][4];
        for(int i=0;i<Count;i++){
            System.out.print("Processes["+i+"] :-> ");
            processData[i][0] = i;
            processData[i][1] = scan.nextInt();
        }
    }
    public void DisplayValue(){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Process Number || Brust Time    || Wating Time  || Turn Around Time");
        System.out.println("------------------------------------------------------------------------");
        for(int i=0;i<Count;i++){
            System.out.println("Processes["+processData[i]
            [0]+"]\t||\t"+processData[i][1]+"\t||\t"+processData[i]
            [2]+"\t||\t"+processData[i][3]);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
    public static void main(String a[]){
        FCFS fs = new FCFS();
        fs.setValue();
        System.out.println("\n\n***********First Come First Server :(FCFS) :***********\n");
        System.out.println("\n\nBefore Scheduling\n");
        fs.DisplayValue();
        fs.scheduleFCFS();
        System.out.println("\n\nAfter Scheduling\n");
        fs.DisplayValue();
    }
}


OUTPUT:
/*
Enter Number Of processes : 3
Processes[0] :-> 12
Processes[1] :-> 8
Processes[2] :-> 9

***********First Come First Server :(FCFS) :***********


Before Scheduling

------------------------------------------------------------------------
Process Number || Brust Time    || Wating Time  || Turn Around Time
------------------------------------------------------------------------
Processes[0]    ||  12  ||  0   ||  0
Processes[1]    ||  8   ||  0   ||  0
Processes[2]    ||  9   ||  0   ||  0
-------------------------------------------------------------------------

After Scheduling

------------------------------------------------------------------------
Process Number || Brust Time    || Wating Time  || Turn Around Time
------------------------------------------------------------------------
Processes[0]    ||  12  ||  0   ||  12
Processes[1]    ||  8   ||  12  ||  20
Processes[2]    ||  9   ||  20  ||  29
-------------------------------------------------------------------------

*/

SJF (Preemptive) Scheduling Algorithms:
import java.util.Scanner;

public class SJF {
    int Count=0;
    int[][] processData;
    Scanner scan = new Scanner(System.in);
    public static void main(String a[]){
        System.out.println("\n\n***********Shortest Job First : (SJF) :***********\n");
        SJF sjf = new SJF();
        sjf.setValue();
        System.out.println("\n\nBefore Scheduling\n");
        sjf.DisplayValue();
        sjf.scheduleSJF();
        System.out.println("\n\nAfter Scheduling\n");
        sjf.DisplayValue();
    }
    public void scheduleSJF(){
        scheduleSorting();
        scheduleCalculation();
    }
    public void scheduleSorting(){
        for(int i=0;i<Count;i++){
            int ele=i;
            int eleData=processData[i][1];
            for(int j=i+1;j<Count;j++){
                if(eleData > processData[j][1]){
                    eleData= processData[j][1];
                    ele=processData[j][0];
                }
            }
            swapProcess(i,ele);
        }
    }
    public void swapProcess(int p1,int p2){
        int[] temp = processData[p1];
        processData[p1] = processData[p2];
        processData[p2] = temp;
    }
    public void scheduleCalculation(){
        processData[0][2] = 0;
        processData[0][3] = processData[0][1];
        for(int i=1;i<Count;i++){
            processData[i][2] = processData[i-1][3];
            processData[i][3] = processData[i][1]+processData[i][2];
        }
    }
    public void setValue(){
        System.out.print("Enter Number Of processes : ");
        Count = scan.nextInt();
        processData = new int[Count][4];
        for(int i=0;i<Count;i++){
            System.out.println("Processes["+i+"] :-> ");
            processData[i][0] = i;
            System.out.print("\t\tBrust Time["+i+"] :-> ");
            processData[i][1] = scan.nextInt();
        }
    }
    public void DisplayValue(){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Process Number || Brust Time || Wating Time || Turn Around Time");
        System.out.println("------------------------------------------------------------------------");
        for(int i=0;i<Count;i++){
            System.out.println("Processes["+processData[i]
            [0]+"]\t||\t"+processData[i][1]+"\t||\t"+processData[i][2]+"\t||\t"+processData[i][3]);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}

/* OUTPUT

***********Shortest Job First : (SJF) :***********

Enter Number Of processes : 3
Processes[0] :-> 
        Brust Time[0] :-> 12
Processes[1] :-> 
        Brust Time[1] :-> 6
Processes[2] :-> 
        Brust Time[2] :-> 9

Before Scheduling

------------------------------------------------------------------------
Process Number || Brust Time || Wating Time || Turn Around Time
------------------------------------------------------------------------
Processes[0]    ||  12  ||  0   ||  0
Processes[1]    ||  6   ||  0   ||  0
Processes[2]    ||  9   ||  0   ||  0
-------------------------------------------------------------------------

After Scheduling

------------------------------------------------------------------------
Process Number || Brust Time || Wating Time || Turn Around Time
------------------------------------------------------------------------
Processes[1]    ||  6   ||  0   ||  6
Processes[2]    ||  9   ||  6   ||  15
Processes[0]    ||  12  ||  15  ||  27
-------------------------------------------------------------------------

 */

PRIORITY Scheduling Algorithms:

import java.util.Scanner;

public class Priority {
    int Count=0;
    int[][] processData;
    Scanner scan = new Scanner(System.in);
    
    public static void main(String a[]){
        System.out.println("\n\n***********Priority Scheduling  :***********\n");
        Priority Prio = new Priority();
        Prio.setValue();
        System.out.println("\n\nBefore Scheduling\n");
        Prio.DisplayValue();
        Prio.schedulePriority();
        System.out.println("\n\nAfter Scheduling\n");
        Prio.DisplayValue();
    }
    
    public void schedulePriority(){
        scheduleSorting();
        scheduleCalculation();
    }
    
    public void scheduleSorting(){
        for(int i=0;i<Count;i++){
            int processIndex=i;
            int processPriority=processData[i][4];
            for(int j=i+1;j<Count;j++){
                if(processPriority > processData[j][4]){
                    processPriority= processData[j][4];
                    processIndex=processData[j][0];
                }
            }
            swapProcess(i,processIndex);
        }
    }
    
    public void swapProcess(int p1,int p2){
        int[] temp = processData[p1];
        processData[p1] = processData[p2];
        processData[p2] = temp;
    }
    
    public void scheduleCalculation(){
        processData[0][2] = 0;
        processData[0][3] = processData[0][1];
        for(int i=1;i<Count;i++){
            processData[i][2] = processData[i-1][3];
            processData[i][3] = processData[i][1]+processData[i][2];
        }
    }
    
    public void setValue(){
        System.out.print("Enter Number Of processes : ");
        Count = scan.nextInt();
        processData = new int[Count][5];
        for(int i=0;i<Count;i++){
            System.out.println("Processes["+i+"] :-> ");
            processData[i][0] = i;
            System.out.print("\t\tBrust Time["+i+"] :-> ");
            processData[i][1] = scan.nextInt();
            System.out.print("\t\tPriority["+i+"] :-> ");
            processData[i][4] = scan.nextInt();
        }
    }
    
    public void DisplayValue(){
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Process Number  || Brust Time   || Wating Time   || Turn Around Time   || Priority");
        System.out.println("--------------------------------------------------------------------------------------");
        for(int i=0;i<Count;i++){
            System.out.println("Processes["+processData[i][0]+"]\t||\t"+processData[i][1]+"\t||\t"+processData[i][2]+"\t||\t"+processData[i][3]+"\t||\t"+processData[i][4]);
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }

}

/* OUTPUT

***********Priority Scheduling  :***********

Enter Number Of processes : 5
Processes[0] :-> 
        Brust Time[0] :-> 16
        Priority[0] :-> 6
Processes[1] :-> 
        Brust Time[1] :-> 12
        Priority[1] :-> 4
Processes[2] :-> 
        Brust Time[2] :-> 6
        Priority[2] :-> 2
Processes[3] :-> 
        Brust Time[3] :-> 18
        Priority[3] :-> 5
Processes[4] :-> 
        Brust Time[4] :-> 14
        Priority[4] :-> 1

Before Scheduling

--------------------------------------------------------------------------------------
Process Number  || Brust Time   || Wating Time   || Turn Around Time   || Priority
--------------------------------------------------------------------------------------
Processes[0]    ||  16  ||  0   ||  0   ||  6
Processes[1]    ||  12  ||  0   ||  0   ||  4
Processes[2]    ||  6   ||  0   ||  0   ||  2
Processes[3]    ||  18  ||  0   ||  0   ||  5
Processes[4]    ||  14  ||  0   ||  0   ||  1
---------------------------------------------------------------------------------------

After Scheduling

--------------------------------------------------------------------------------------
Process Number  || Brust Time   || Wating Time   || Turn Around Time   || Priority
--------------------------------------------------------------------------------------
Processes[4]    ||  14  ||  0   ||  14  ||  1
Processes[2]    ||  6   ||  14  ||  20  ||  2
Processes[1]    ||  12  ||  20  ||  32  ||  4
Processes[3]    ||  18  ||  32  ||  50  ||  5
Processes[0]    ||  16  ||  50  ||  66  ||  6
---------------------------------------------------------------------------------------

*/

ROUND ROBIN Scheduling Algorithms:
import java.util.Scanner;

public class RoundRobin {
    int Count=0;
    int[][] processData;
    int[] CCycle; // Complete Cycle
    int[] PCycle; // Partial Cycle
    int[] TCycle; // Total Cycle
    String[] Queue;
    boolean[] Status;
    int[] startQue;
    int[] endQue;
    int RTT = 0;
    
    Scanner scan = new Scanner(System.in);
    
    public void scheduleRR(){
        int TotalProcess = CCycle.length;
        for(int i = 0,j=0;i < Queue.length || j<Queue.length;i++)
{
            int ProNo = i % TotalProcess;
            if(CCycle[ProNo] != 0){
                Queue[j] = "process["+ProNo+"]";
                if(j==0)
                    startQue[j] = 0;
                else
                    startQue[j] = endQue[j-1] + 1;

                if(!Status[ProNo]){
                    processData[ProNo][2] = startQue[j];
                    Status[ProNo] = true;
                }
                endQue[j] = startQue[j]+RTT-1;
                j++;
                CCycle[ProNo] = CCycle[ProNo] - 1;
            }else{
                if(Status[ProNo]){
                    Queue[j] = "process["+ProNo+"]";
                    if(j==0)
                        startQue[j] = 0;
                    else
                        startQue[j] = endQue[j-1] + 1;
                    endQue[j] = startQue[j]+PCycle[ProNo]-1;
                    processData[ProNo][3] = endQue[j];
                    PCycle[ProNo]=0;
                    j++;
                    Status[ProNo]= false;
                }
            }
        }
        System.out.println("Queue : start : End : Interval : ");
        for (int i = 0; i < Queue.length ; i++){
            int temp = (endQue[i]-startQue[i]+1);
            System.out.println(" "+Queue[i]+" "+startQue[i]+" "+endQue[i] +" "+temp );
            if(i%Count == Count-1)
                System.out.println("");
        }
    }
    public void setValue(){
        System.out.print("Enter Number Of processes : ");
        Count = scan.nextInt();
        System.out.print("Enter Round Trip Time : ");
        RTT = scan.nextInt();
        processData = new int[Count][4];
        CCycle = new int[Count];
        PCycle = new int[Count];
        TCycle = new int[Count];
        for(int i=0;i<Count;i++){
            System.out.print("Processes["+i+"] :-> ");
            processData[i][0] = i;
            processData[i][1] = scan.nextInt();
            CCycle[i] = processData[i][1]/RTT;
            PCycle[i] = processData[i][1]%RTT;
            if(PCycle[i]==0)
                TCycle[i] = CCycle[i];
            else
                TCycle[i] = CCycle[i] + 1;
            System.out.println("P["+i+"] CC : "+CCycle[i]+" PC : "+PCycle[i]+" TC :"+TCycle[i]);
        }
        Status = new boolean[Count];
        for(int i=0;i<Count;i++){
            Status[i]= false;
        }
        initQueue();
    }
    public void initQueue(){
        int MaxQueLen = 0;
        for(int i=0;i<TCycle.length;i++){
            MaxQueLen = MaxQueLen + TCycle[i];
        }
        Queue = new String[MaxQueLen];
        startQue = new int[MaxQueLen];
        endQue = new int[MaxQueLen];
        System.out.println("Queue Length : "+MaxQueLen);
    }
    public void DisplayValue(){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Process Number || Brust Time || Wating Time || Turn Around Time");
        System.out.println("------------------------------------------------------------------------");
        for(int i=0;i<Count;i++){
            System.out.println("Processes["+processData[i]
            [0]+"]\t||\t"+processData[i][1]+"\t||\t"+processData[i][2]+"\t||\t"+processData[i][3]);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
    public static void main(String a[]){
        RoundRobin rr = new RoundRobin();
        rr.setValue();
        System.out.println("\n\n***********Round Robin : (RR) :***********\n");
        System.out.println("\n\nBefore Scheduling\n");
        rr.DisplayValue();
        rr.scheduleRR();
        System.out.println("\n\nAfter Scheduling\n");
        rr.DisplayValue();
    }
}

/* OUTPUT
Enter Number Of processes : 5
Enter Round Trip Time : 3
Processes[0] :-> 11
P[0] CC : 3 PC : 2 TC :4
Processes[1] :-> 16
P[1] CC : 5 PC : 1 TC :6
Processes[2] :-> 13
P[2] CC : 4 PC : 1 TC :5
Processes[3] :-> 25
P[3] CC : 8 PC : 1 TC :9
Processes[4] :-> 23
P[4] CC : 7 PC : 2 TC :8
Queue Length : 32

***********Round Robin : (RR) :***********


Before Scheduling

------------------------------------------------------------------------
Process Number || Brust Time || Wating Time || Turn Around Time
------------------------------------------------------------------------
Processes[0]    ||  11  ||  0   ||  0
Processes[1]    ||  16  ||  0   ||  0
Processes[2]    ||  13  ||  0   ||  0
Processes[3]    ||  25  ||  0   ||  0
Processes[4]    ||  23  ||  0   ||  0
-------------------------------------------------------------------------
Queue : start : End : Interval : 
 process[0] 0 2 3
 process[1] 3 5 3
 process[2] 6 8 3
 process[3] 9 11 3
 process[4] 12 14 3

 process[0] 15 17 3
 process[1] 18 20 3
 process[2] 21 23 3
 process[3] 24 26 3
 process[4] 27 29 3

 process[0] 30 32 3
 process[1] 33 35 3
 process[2] 36 38 3
 process[3] 39 41 3
 process[4] 42 44 3

 process[0] 45 46 2
 process[1] 47 49 3
 process[2] 50 52 3
 process[3] 53 55 3
 process[4] 56 58 3

 process[1] 59 61 3
 process[2] 62 62 1
 process[3] 63 65 3
 process[4] 66 68 3
 process[1] 69 69 1

 process[3] 70 72 3
 process[4] 73 75 3
 process[3] 76 78 3
 process[4] 79 81 3
 process[3] 82 84 3

 process[4] 85 86 2
 process[3] 87 87 1

After Scheduling

------------------------------------------------------------------------
Process Number || Brust Time || Wating Time || Turn Around Time
------------------------------------------------------------------------
Processes[0]    ||  11  ||  0   ||  46
Processes[1]    ||  16  ||  3   ||  69
Processes[2]    ||  13  ||  6   ||  62
Processes[3]    ||  25  ||  9   ||  87
Processes[4]    ||  23  ||  12  ||  86
-------------------------------------------------------------------------

*/



EXPERIMENT NO.04
Title: Write a program to simulate Page replacement algorithm
1.	LRU Page replacement algorithm:
Program:
import java.io.*;
import java.util.*;
public class LRU {
public static void main(String[] args) throws IOException
{
BufferedReader br = new BufferedReader(new
InputStreamReader(System.in));
int frames,pointer = 0, hit = 0, fault = 0,ref_len;
Boolean isFull = false;
int buffer[];
ArrayList<Integer> stack = new ArrayList<Integer>();
int reference[];
int mem_layout[][];
System.out.println("Please enter the number of Frames: ");
frames = Integer.parseInt(br.readLine());
System.out.println("Please enter the length of the Reference string:");
ref_len = Integer.parseInt(br.readLine());
reference = new int[ref_len];
mem_layout = new int[ref_len][frames];
buffer = new int[frames];
for(int j = 0; j < frames; j++)
buffer[j] = -1;
System.out.println("Please enter the reference string: ");
for(int i = 0; i < ref_len; i++)
{
reference[i] = Integer.parseInt(br.readLine());
}
System.out.println();
for(int i = 0; i < ref_len; i++)
{
if(stack.contains(reference[i]))
{
stack.remove(stack.indexOf(reference[i]));
}
stack.add(reference[i]);
int search = -1;
for(int j = 0; j < frames; j++)
{
if(buffer[j] == reference[i])
{
search = j;
hit++;
break;}
}
if(search == -1)
{
if(isFull)
{
int min_loc = ref_len;
for(int j = 0; j < frames; j++)
{
if(stack.contains(buffer[j]))
{
int temp = stack.indexOf(buffer[j]);
if(temp < min_loc)
{
min_loc = temp;
pointer = j;
}
}
}
}
buffer[pointer] = reference[i];
fault++;
pointer++;
if(pointer == frames)
{
pointer = 0;
isFull = true;
}
}
for(int j = 0; j < frames; j++)
mem_layout[i][j] = buffer[j];
}
for(int i = 0; i < frames; i++)
{
for(int j = 0; j < ref_len; j++)
System.out.printf("%3d ",mem_layout[j][i]);
System.out.println();
}
System.out.println("The number of Hits: " + hit);
System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
System.out.println("The number of Faults: " + fault);
}
}



Output:
Microsoft Windows [Version 10.0.19042.1288]
(c) Microsoft Corporation. All rights reserved.

C:\Users\User>cd \

C:\>cd SPOS

C:\SPOS>cd pagereplace

C:\SPOS\pagereplace>javac LRU.java

C:\SPOS\pagereplace>java LRU
Please enter the number of Frames:
4
Please enter the length of the Reference string:
6
Please enter the reference string:
3
5
2
8
1
7

  3   3   3   3   1   1
 -1   5   5   5   5   7
 -1  -1   2   2   2   2
 -1  -1  -1   8   8   8
The number of Hits: 0
Hit Ratio: 0.0
The number of Faults: 6

C:\SPOS\pagereplace>


2.OPTIMAL Page Replacement Algorithm:
Program:
import java.io.*;
import java.util.*;
//import
public
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
class OptimalReplacement {
public static void main(String[] args) throws IOException
{
BufferedReader br = new BufferedReader(new
InputStreamReader(System.in));
int frames, pointer = 0, hit = 0, fault = 0,ref_len;
boolean isFull = false;
int buffer[];
int reference[];
int mem_layout[][];
System.out.println("Please enter the number of Frames: ");
frames = Integer.parseInt(br.readLine());
System.out.println("Please enter the length of the Reference string:");
ref_len = Integer.parseInt(br.readLine());
reference = new int[ref_len];
mem_layout = new int[ref_len][frames];
buffer = new int[frames];
for(int j = 0; j < frames; j++)
buffer[j] = -1;
System.out.println("Please enter the reference string: ");
for(int i = 0; i < ref_len; i++)
{
reference[i] = Integer.parseInt(br.readLine());
}
System.out.println();
for(int i = 0; i < ref_len; i++)
{
int search = -1;
for(int j = 0; j < frames; j++)
{
if(buffer[j] == reference[i])
{
search = j;
hit++;
break;
}
}
if(search == -1)
{
if(isFull)
{
int index[] = new int[frames];
boolean index_flag[] = new boolean[frames];for(int j = i + 1; j < ref_len; j++)
{
for(int k = 0; k < frames; k++)
{
if((reference[j] == buffer[k]) && (index_flag[k] == false))
{
index[k] = j;
index_flag[k] = true;
break;
}
}
}
int max = index[0];
pointer = 0;
if(max == 0)
max = 200;
for(int j = 0; j < frames; j++)
{
if(index[j] == 0)
index[j] = 200;
if(index[j] > max)
{
max = index[j];
pointer = j;
}
}
}
buffer[pointer] = reference[i];
fault++;
if(!isFull)
{
pointer++;
if(pointer == frames)
{
pointer = 0;
isFull = true;
}
}
}
for(int j = 0; j < frames; j++)
mem_layout[i][j] = buffer[j];
}
for(int i = 0; i < frames; i++)
{
for(int j = 0; j < ref_len; j++)
System.out.printf("%3d ",mem_layout[j][i]);
System.out.println();
}
System.out.println("The number of Hits: " + hit);
System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
System.out.println("The number of Faults: " + fault);
}
}


Output:
Microsoft Windows [Version 10.0.19042.1288]
(c) Microsoft Corporation. All rights reserved.

C:\Users\User>cd \

C:\>cd SPOS

C:\SPOS>cd pagereplace

C:\SPOS\pagereplace>javac OptimalReplacement.java

C:\SPOS\pagereplace>java OptimalReplacement
Please enter the number of Frames:
5
Please enter the length of the Reference string:
4
Please enter the reference string:
5
6
3
5

  5   5   5   5
 -1   6   6   6
 -1  -1   3   3
 -1  -1  -1  -1
 -1  -1  -1  -1
The number of Hits: 1
Hit Ratio: 0.25
The number of Faults: 3

C:\SPOS\pagereplace>


