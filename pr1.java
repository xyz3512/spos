import java.util.*;

class Assembler1 {
    ArrayList<ArrayList<String>> ic = null;
    ArrayList<ArrayList<String>> sym = null;
    ArrayList<ArrayList<String>> lit = null;
    ArrayList<ArrayList<String>> pol = null;

    // ---------- PASS 1: Assign Location Counter ----------
    public String[][] assignLC(String[][] asm) {
        int start_value = 0;

        for (int i = 0; i < asm.length; i++) {
            for (int j = 0; j < asm[i].length; j++) {
                if (asm[i][j] != null && asm[i][j].equals("START") && j + 1 < asm[i].length) {
                    start_value = Integer.parseInt(asm[i][j + 1]);
                }
            }
            if (i == 0) continue; // Skip first line (START)
            start_value++;
            asm[i][0] = String.valueOf(start_value);
        }
        return asm;
    }

    // ---------- Print Assembler Code with LCs ----------
    public void printCode(String[][] asm) {
        System.out.println("\n--- Assembly Code with LC ---");
        for (String[] row : asm) {
            for (String col : row) {
                System.out.printf("%8s", col == null ? "" : col);
            }
            System.out.println();
        }
    }

    // ---------- Generate Intermediate Code and Tables ----------
    public void generateOutput(String[][] asm, String[][] mot) {
        ArrayList<ArrayList<String>> ic = new ArrayList<>(asm.length);
        ArrayList<ArrayList<String>> sym = new ArrayList<>(asm.length);
        ArrayList<ArrayList<String>> lit = new ArrayList<>(asm.length);
        ArrayList<ArrayList<String>> pol = new ArrayList<>(asm.length);

        for (int i = 0; i < asm.length; i++) {
            ic.add(new ArrayList<>());
            sym.add(new ArrayList<>());
            lit.add(new ArrayList<>());
            pol.add(new ArrayList<>());
        }

        int sym_index = 0, lit_index = 0, pol_index = 0;

        for (int i = 0; i < asm.length; i++) {
            for (int j = 0; j < asm[i].length; j++) {
                if (asm[i][j] == null || asm[i][j].isEmpty()) continue;

                // --- Machine Opcode Table Match ---
                for (int m = 0; m < mot.length; m++) {
                    if (asm[i][j].equals(mot[m][0])) {
                        String icStr = "(" + mot[m][1] + "," + mot[m][2] + ")";
                        ic.get(i).add(icStr);
                    }
                }

                // --- Pool Table (for LTORG, END) ---
                if (asm[i][j].equals("LTORG") || asm[i][j].equals("END")) {
                    String polStr = "(" + pol_index + "," + asm[i][j] + "," + asm[i][0] + ")";
                    pol.get(i).add(polStr);
                    pol_index++;
                }

                // --- Symbol Table ---
                if (asm[i][j].matches("[a-zA-Z][a-zA-Z0-9]*")) {
                    String symStr = "(" + sym_index + "," + asm[i][j] + "," + asm[i][0] + ")";
                    sym.get(i).add(symStr);
                    sym_index++;
                    String icStr = "(S," + asm[i][j] + ")";
                    ic.get(i).add(icStr);
                }

                // --- Literal Table ---
                if (asm[i][j].matches("[0-9]+") && !asm[i][j].equals(asm[i][0])) {
                    String litStr = "(" + lit_index + "," + asm[i][j] + "," + asm[i][0] + ")";
                    lit.get(i).add(litStr);
                    lit_index++;
                }
            }
        }

        this.ic = ic;
        this.sym = sym;
        this.lit = lit;
        this.pol = pol;
    }
}

// ---------- MAIN CLASS ----------
public class PR1 {
    public static void main(String[] args) {
        String asm_code[][] = {
                {"", "", "START", "200", ""},
                {"", "", "MOVER", "AREG", "X"},
                {"", "", "MOVEM", "BREG", "Y"},
                {"", "X", "DS", "1", ""},
                {"", "", "END", "", ""}
        };

        String mot[][] = {
                {"STOP", "IS", "00"}, {"ADD", "IS", "01"}, {"SUB", "IS", "02"},
                {"MULT", "IS", "03"}, {"MOVER", "IS", "04"}, {"MOVEM", "IS", "05"},
                {"COMP", "IS", "06"}, {"BC", "IS", "07"}, {"DIV", "IS", "08"},
                {"READ", "IS", "09"}, {"PRINT", "IS", "10"}, {"START", "AD", "01"},
                {"END", "AD", "02"}, {"ORIGIN", "AD", "03"}, {"EQU", "AD", "04"},
                {"LTORG", "AD", "05"}, {"DS", "DL", "01"}, {"DC", "DL", "02"},
                {"AREG", "RG", "01"}, {"BREG", "RG", "02"}, {"CREG", "RG", "03"},
        };

        Assembler1 assembler = new Assembler1();
        String[][] asm_with_lc = assembler.assignLC(asm_code);

        assembler.printCode(asm_with_lc);
        assembler.generateOutput(asm_with_lc, mot);

        // ---------- OUTPUT ----------
        System.out.println("\n--- Intermediate Code (IC) ---");
        for (ArrayList<String> str : assembler.ic) {
            for (String st : str) System.out.print(st + " ");
            System.out.println();
        }

        System.out.println("\n--- Symbol Table (SYMTAB) ---");
        for (ArrayList<String> str : assembler.sym) {
            for (String st : str) System.out.println(st);
        }

        System.out.println("\n--- Literal Table (LITTAB) ---");
        for (ArrayList<String> str : assembler.lit) {
            for (String st : str) System.out.println(st);
        }

        System.out.println("\n--- Pool Table (POOLTAB) ---");
        for (ArrayList<String> str : assembler.pol) {
            for (String st : str) System.out.println(st);
        }
    }
}
