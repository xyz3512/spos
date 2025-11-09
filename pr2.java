import java.util.*;

class PR2 {
    static String mnt[][] = new String[6][3]; // assuming 5 macros in 1 program
    static String ala[][] = new String[10][2]; // assuming 2 arguments in each macro
    static String alax[][] = new String[10][3];
    static String mdt[][] = new String[20][1]; // assuming 4 LOC for each macro
    static int mntc = 0, mdtc = 0, cnt = 0;

    public static void main(String args[]) {
        pass1();
        System.out.println("\n********* PASS-1 MACROPROCESSOR ***********\n");

        System.out.println("MACRO NAME TABLE (MNT)\nSr.\tMacro\tLoc");
        display(mnt, mntc, 3);

        System.out.println("\nARGUMENT LIST ARRAY (ALA)");
        display(alax, cnt, 3);

        System.out.println("\nMACRO DEFINITION TABLE (MDT)\nSr.\tMDT");
        display(mdt, mdtc, 1);
    }

    static void pass1() {
        // 🔹 Example macro program directly inside the code
        String[] input = {
                "MACRO",
                "INCR &ARG1,&ARG2",
                "ADD &ARG1,AREG",
                "SUB &ARG2,AREG",
                "MEND",
                "MACRO",
                "MULTI &X",
                "MOVER &X,AREG",
                "MEND",
                "START",
                "INCR A,B",
                "MULTI C",
                "END"
        };

        String s, prev = "";
        int index = 0;

        try {
            for (int line = 0; line < input.length; line++) {
                s = input[line];
                if (s.equalsIgnoreCase("MACRO")) {
                    int flag = 0;
                    prev = s;

                    for (prev = s; !(s = input[++line]).equalsIgnoreCase("MEND"); mdtc++) {
                        if (prev.equalsIgnoreCase("MACRO")) {
                            StringTokenizer st = new StringTokenizer(s);
                            String str[] = new String[st.countTokens()];
                            for (int i = 0; i < str.length; i++)
                                str[i] = st.nextToken();

                            if (flag == 0) {
                                mnt[mntc][0] = (mntc + 1) + "";
                                mnt[mntc][1] = str[0];
                                mnt[mntc++][2] = (mdtc) + "";

                                ALA(str[1], cnt);
                                flag = 1;
                            }
                        }

                        if (flag == 1) {
                            if (s.contains("&")) {
                                String tokens1[] = s.split("[, ]");
                                for (int i = 0; i < tokens1.length; i++) {
                                    for (int m = 0; m < 3; m++) {
                                        if (alax[cnt][m] != null && tokens1[i].equalsIgnoreCase(alax[cnt][m]))
                                            s = s.replace(tokens1[i], "#" + m);
                                    }
                                }
                            }
                        }

                        mdt[mdtc][0] = mdtc + "  " + s;
                    }
                    mdt[mdtc++][0] = mdtc + "  " + s;
                    cnt++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void ALA(String str, int count) {
        StringTokenizer st = new StringTokenizer(str, ",");
        int i = 0;
        while (st.hasMoreTokens() && i < 3) {
            String token = st.nextToken();
            int index = token.indexOf("=");
            if (index != -1)
                alax[count][i] = token.substring(0, index);
            else
                alax[count][i] = token;
            i++;
        }
        while (i < 3) {
            alax[count][i] = " ";
            i++;
        }
    }

    static void display(String a[][], int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == null)
                    a[i][j] = " ";
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
