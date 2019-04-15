// Este é um arquivo de esqueleto do projeto

import java.io.PrintStream;

/** Esta classe agrega todos os tipos de rotinas e constantes de suporte
     para o gerador de código; todas as rotinas são estáticas, então nenhuma instância de
     esta classe é criada mesmo. */
class CgenSupport {
    /** Constantes de tempo de execução para controlar o coletor de lixo. */
    final static String[] gcInitNames = {
	"_NoGC_Init", "_GenGC_Init", "_ScnGC_Init"
    };

    /** Constantes de tempo de execução para controlar o coletor de lixo. */
    final static String[] gcCollectNames = {
	"_NoGC_Collect", "_GenGC_Collect", "_ScnGC_Collect" 
    };

    final static int MAXINT = 100000000;
    final static int WORD_SIZE = 4;
    final static int LOG_WORD_SIZE = 2;     // para mudanças lógicas

    // Nomes globais
    final static String CLASSNAMETAB = "class_nameTab";
    final static String CLASSOBJTAB  = "class_objTab";
    final static String INTTAG       = "_int_tag";
    final static String BOOLTAG      = "_bool_tag";
    final static String STRINGTAG    = "_string_tag";
    final static String HEAP_START   = "heap_start";

    //  Nomenclatura
    final static String DISPTAB_SUFFIX      = "_dispTab";
    final static String METHOD_SEP          = ".";
    final static String CLASSINIT_SUFFIX    = "_init";
    final static String PROTOBJ_SUFFIX      = "_protObj";
    final static String OBJECTPROTOBJ       = "Object" + PROTOBJ_SUFFIX;
    final static String INTCONST_PREFIX     = "int_const";
    final static String STRCONST_PREFIX     = "str_const";
    final static String BOOLCONST_PREFIX    = "bool_const";

    final static String CLASSOBJECTTABLE = "class_objTab";
    final static String OBJECTCOPY = "Object.copy";


    final static int    EMPTYSLOT           = 0;
    final static String LABEL               = ":\n";

    // informações sobre cabeçalhos de objetos
    final static int DEFAULT_OBJFIELDS = 3;
    final static int TAG_OFFSET = 0;
    final static int SIZE_OFFSET = 1;
    final static int DISPTABLE_OFFSET = 2;

    final static int STRING_SLOTS      = 1;
    final static int INT_SLOTS         = 1;
    final static int BOOL_SLOTS        = 1;

    final static String GLOBAL       = "\t.globl\t"; 
    final static String ALIGN        = "\t.align\t2\n";
    final static String WORD         = "\t.word\t";

    // registros de nomes
    final static String ZERO= "$zero";		// Zero de registro 
    final static String ACC = "$a0";		// Acumulador 
    final static String A1  = "$a1";		// Para argumentos para prim funcs
    final static String SELF= "$s0";		// Ptr to self (callee saves) 
    final static String S1=   "$s1";		
    // não pode ser substituído por outros regs
    final static String T1  = "$t1";		// temporario 1 
    final static String T2  = "$t2";            // temporario 2 
    final static String T3  = "$t3";            // temporario 3 
    final static String SP  = "$sp";		// Pilha de ponteiros 
    final static String FP  = "$fp";		// Frame ponteiro 
    final static String RA  = "$ra";		// retorna enderesso  
    
    // Opcodes
    final static String JALR = "\tjalr\t";
    final static String JAL  = "\tjal\t";               
    final static String RET  = "\tjr\t" + RA + "\t";
    
    final static String SW   = "\tsw\t";
    final static String LW   = "\tlw\t";
    final static String LI   = "\tli\t";
    final static String LA   = "\tla\t";

    final static String MOVE = "\tmove\t";
    final static String NEG  = "\tneg\t";
    final static String ADD  = "\tadd\t";
    final static String ADDI = "\taddi\t";
    final static String ADDU = "\taddu\t";
    final static String ADDIU= "\taddiu\t";
    final static String DIV  = "\tdiv\t";
    final static String MUL  = "\tmul\t";
    final static String SUB  = "\tsub\t";
    final static String SLL  = "\tsll\t";
    final static String BEQZ = "\tbeqz\t";
    final static String BRANCH  = "\tb\t";
    final static String BEQ     = "\tbeq\t";
    final static String BNE     = "\tbne\t";
    final static String BLEQ    = "\tble\t";
    final static String BLT     = "\tblt\t";
    final static String BGT     = "\tbgt\t";

    static final String DISPATCH_ABORT = "_dispatch_abort";
    static final String CASE_ABORT = "_case_abort";
    static final String CASE_ABORT2 = "_case_abort2";

    public static int label = 0;
    public static int classTagNumber = 5; //0-4: OBJ, IO, INT, BOOL, STR
    public static boolean usedS1 = true;
    public static boolean usedAcc = false;
    static int getLabel() { label++; return label - 1; }
    static int getTagNumber() { classTagNumber++; return classTagNumber - 1; }

    public static int curMethodTempVarNumber = 0;
    public static int getCurrentMethodTempVarNumber() {
        return curMethodTempVarNumber;
    }
    public static void setCurrentMethodTempVarNumber(int number) {
        curMethodTempVarNumber = number;
    }


    /** Emite uma instrução LW.
      * @param dest_reg o registro de destino
      * @param compensa a palavra offset do registrador de origem
      * @param source_reg o registrador de origem
      * @param s o fluxo de saída
     * */
    static void emitLoad(String dest_reg, int offset, String source_reg, 
			 PrintStream s) {
	s.println(LW + dest_reg + " " 
		  + offset * WORD_SIZE 
		  + "(" + source_reg + ")");
    }

    /** Emite uma instrução SW.
      * @param dest_reg o registro de destino
      * @param compensa a palavra offset do registrador de origem
      * @param source_reg o registrador de origem
      * @param s o fluxo de saídaEntrega uma instrução SW.
      * @param dest_reg o registro de destino
      * @param compensa a palavra offset do registrador de origem
      * @param source_reg o registrador de origem
      * @param s o fluxo de saída
     * */
    static void emitStore(String source_reg, int offset, String dest_reg, 
			  PrintStream s) {
	s.println(SW + source_reg + " " 
		  + offset * WORD_SIZE 
		  + "(" + dest_reg + ")");
    }

    /** Emite a instrução LI.
      * @param dest_reg o registro de destino
      * @param val o valor inteiro
      * @param s o fluxo de saída
     * */
    static void emitLoadImm(String dest_reg, int val, PrintStream s) {
	s.println(LI + dest_reg + " " + val);
    }

    /** Emite uma instrução de LA.
      * @param dest_reg o registro de destino
      * @param endereça o endereço do qual uma palavra é carregada
      * @param s o fluxo de saída
     * */
    static void emitLoadAddress(String dest_reg, String address, PrintStream s){
	s.println(LA + dest_reg + " " + address);
    }

    /** Emite uma instrução LA sem a parte do endereço.
      * @param dest_reg o registro de destino
      * @param s o fluxo de saída
     * */
    static void emitPartialLoadAddress(String dest_reg, PrintStream s) {
	s.print(LA + dest_reg + " ");
    }

    /** Emite uma instrução para carregar uma constante booleana em um registrador.
      * @param dest_reg o registro de destino
      * @param b a constante booleana
      * @param s o fluxo de saída
     * */
    static void emitLoadBool(String dest_reg, BoolConst b, PrintStream s) {
	emitPartialLoadAddress(dest_reg, s);
	b.codeRef(s);
	s.println("");
    }

    /** Emite uma instrução para carregar uma constante de string em um registrador.
      * @param dest_reg o registro de destino
      * @param str a string constante
      * @param s o fluxo de saída
     * */
    static void emitLoadString(String dest_reg, StringSymbol str, 
			       PrintStream s) {
	emitPartialLoadAddress(dest_reg, s);
	str.codeRef(s);
	s.println("");
    }

    /**Emite uma instrução para carregar uma constante inteira em um registrador.
      * @param dest_reg o registro de destino
      * @param i a constante inteira
      * @param s o fluxo de saída
     * */
    static void emitLoadInt(String dest_reg, IntSymbol i, PrintStream s) {
	emitPartialLoadAddress(dest_reg, s);
	i.codeRef(s);
	s.println("");
    }

    /** Emite uma instrução MOVE.
      * @param dest_reg o registro de destino
      * @param source_reg o registrador de origem
      * @param s o fluxo de saída
     * */
    static void emitMove(String dest_reg, String source_reg, PrintStream s) {
	s.println(MOVE + dest_reg + " " + source_reg);
    }

    /** Emite uma instrução NEG.
      * @param dest_reg o registro de destino
      * @param source_reg o registrador de origem
      * @param s o fluxo de saída
     * */
    static void emitNeg(String dest_reg, String source_reg, PrintStream s) {
	s.println(NEG + dest_reg + " " + source_reg);
    }
    
    /**Emite uma instrução ADD.
      * @param dest_reg o registro de destino
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param s o fluxo de saída
     * */
    static void emitAdd(String dest_reg, String src1, String src2,
			PrintStream s) {
	s.println(ADD + dest_reg + " " + src1 + " " + src2);
    }

    /** Emite uma instrução ADDU.
      * @param dest_reg o registro de destino
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param s o fluxo de saída
     * */
    static void emitAddu(String dest_reg, String src1, String src2,
			PrintStream s) {
	s.println(ADDU + dest_reg + " " + src1 + " " + src2);
    }

    /** emite uma instrução ADDIU.
      * @param dest_reg o registro de destino
      * @param src o registrador de origem
      * @param im o imediato
      * @param s o fluxo de saída
     * */
    static void emitAddiu(String dest_reg, String src, int imm,
			  PrintStream s) {
	s.println(ADDIU + dest_reg + " " + src + " " + imm);
    }

    /** Emite uma instrução DIV.
      * @param dest_reg o registro de destino
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param s o fluxo de saída
     * */
    static void emitDiv(String dest_reg, String src1, String src2,
			PrintStream s) {
	s.println(DIV + dest_reg + " " + src1 + " " + src2);
    }

    /** Emite uma instrução MUL.
      * @param dest_reg o registro de destino
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param s o fluxo de saída
     * */
    static void emitMul(String dest_reg, String src1, String src2,
			PrintStream s) {
	s.println(MUL + dest_reg + " " + src1 + " " + src2);
    }

    /** Emite uma instrução SUB.
      * @param dest_reg o registro de destino
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param s o fluxo de saída
     * */
    static void emitSub(String dest_reg, String src1, String src2,
			PrintStream s) {
	s.println(SUB + dest_reg + " " + src1 + " " + src2);
    }

    /** Emite uma instrução SLL.
      * @param dest_reg o registro de destino
      * @ param src1 o registrador de origem 1
      * @param numera o número de bits a serem deslocados
      * @param s o fluxo de saída
     * */
    static void emitSll(String dest_reg, String src1, int num, PrintStream s) {
	s.println(SLL + dest_reg + " " + src1 + " " + num);
    }

    /**Emite uma instrução JALR.
      * @param dest_reg o registrador com endereço de destino
      * @param s o fluxo de saída
     * */
    static void emitJalr(String dest_reg, PrintStream s) {
	s.println(JALR + dest_reg);
    }

    /** Emite uma instrução JAL.
      * @param dest o endereço ou rótulo alvo
      * @param s o fluxo de saída
     * */
    static void emitJal(String dest, PrintStream s) {
	s.println(JAL + dest);
    }

    /** Emite uma instrução RET.
      * @param s o fluxo de saída
     * */
    static void emitReturn(PrintStream s) {
	s.println(RET);
    }

    /** Emite uma chamada para gc_assign.
      * @param s o fluxo de saída
     * */
    static void emitGCAssign(PrintStream s) {
	s.println(JAL + "_GenGC_Assign");
    }

    /** Emite uma referência para a tabela de despacho.
      * @param sym o nome da classe
      * @param s o fluxo de saída
     * */
    static void emitDispTableRef(AbstractSymbol sym, PrintStream s) {
	s.print(sym + DISPTAB_SUFFIX);
    }

    /** Emite uma referência ao método init () da classe.
      * @param sym o nome da classe
      * @param s o fluxo de saída
     * */
    static void emitInitRef(AbstractSymbol sym, PrintStream s) {
	s.print(sym + CLASSINIT_SUFFIX);
    }

    /** Emite uma referência ao objeto de protótipo da classe.
      * @param sym o nome da classe
      * @param s o fluxo de saída
     * */
    static void emitProtObjRef(AbstractSymbol sym, PrintStream s) {
	s.print(sym + PROTOBJ_SUFFIX);
    }

    /** Emite uma referência a um método em uma classe
      * @param classname o nome da classe
      * @param methodname o nome do método
      * @param s o fluxo de saída
     * */
    static void emitMethodRef(AbstractSymbol classname, 
			      AbstractSymbol methodname, 
			      PrintStream s) {
	s.print(classname + METHOD_SEP + methodname);
    }
    
    /** Emite uma referência a um rótulo
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitLabelRef(int label, PrintStream s) {
	s.print("label" + label);
    }

    /** Emite uma definição de rótulo
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitLabelDef(int label, PrintStream s) {
	emitLabelRef(label, s);
	s.println(":");
    }

    /** Emite uma instrução BEQZ.
      * @param src o registrador de origem
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitBeqz(String src, int label, PrintStream s) {
	s.print(BEQZ + src + " ");
	emitLabelRef(label, s);
	s.println("");
    }
    
    /** Emite uma instrução BEQZ.
      * @param src o registrador de origem
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitBeq(String src1, String src2, int label, PrintStream s) {
	s.print(BEQ + src1 + " " + src2 + " ");
	emitLabelRef(label, s);
	s.println("");
    }

    /** Emite uma instrução BNE.
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitBne(String src1, String src2, int label, PrintStream s) {
	s.print(BNE + src1 + " " + src2 + " ");
	emitLabelRef(label, s);
	s.println("");
    }
    
    /** Emite uma instrução BLEQ.
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitBleq(String src1, String src2, int label, PrintStream s) {
	s.print(BLEQ + src1 + " " + src2 + " ");
	emitLabelRef(label, s);
	s.println("");
    }
    
    /**Emite uma instrução BLT.
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitBlt(String src1, String src2, int label, PrintStream s) {
	s.print(BLT + src1 + " " + src2 + " ");
	emitLabelRef(label, s);
	s.println("");
    }

    /** Emite uma instrução BLT.
      * @ param src1 o registrador de origem 1
      * @ param src2 o registrador de origem 2
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitBlti(String src, int imm, int label, PrintStream s) {
	s.print(BLT + src + " " + imm + " ");
	emitLabelRef(label, s);
	s.println("");
    }

    /** Emite uma instrução BGTI.
      * @param src o registrador de origem
      * @param im o imediato
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitBgti(String src, int imm, int label, PrintStream s) {
	s.print(BGT + src + " " + imm + " ");
	emitLabelRef(label, s);
	s.println("");
    }

    /** Emite uma instrução BRANCH.
      * @param rotular o número do rótulo
      * @param s o fluxo de saída
     * */
    static void emitBranch(int label, PrintStream s) {
	s.print(BRANCH);
	emitLabelRef(label, s);
	s.println("");
    }

    /** Emita uma sequência de instruções para empurrar um registrador para a pilha.
      * A pilha cresce em direção a endereços menores.
      * @param reg o registro
      * @param s o fluxo de saída
     * */
    static void emitPush(String reg, PrintStream s) {
	emitStore(reg, 0, SP, s);
	emitAddiu(SP, SP, -4, s);
    }

    /** Emite um código para buscar o valor inteiro do objeto Integer.
      * @param source um ponteiro para o objeto Integer
      * @param dest o registro de destino para o valor
      * @param s o fluxo de saída
     * */
    static void emitFetchInt(String dest, String source, PrintStream s) {
	emitLoad(dest, DEFAULT_OBJFIELDS, source, s);
    }

    /** Emite o código para armazenar o valor inteiro do objeto Integer.
      * @param fonte um valor inteiro
      * @param dest o ponteiro para um objeto inteiro
      * @param s o fluxo de saída
     * */
    static void emitStoreInt(String source, String dest, PrintStream s) {
	emitStore(source, DEFAULT_OBJFIELDS, dest, s);
    }
    
    /** Emite código para manipular o coletor de lixo
      * @param s o fluxo de saída
     * */
    static void emitTestCollector(PrintStream s) {
	emitPush(ACC, s);
	emitMove(ACC, SP, s);
	emitMove(A1, ZERO, s);
	s.println(JAL + gcCollectNames[Flags.cgen_Memmgr]);
	emitAddiu(SP, SP, 4, s);
	emitLoad(ACC, 0, SP, s);
    }

    /** 
76/5000
Emite código para verificar o coletor de lixo
      * @param s o fluxo de saída
     * */
    static void emitGCCheck(String source, PrintStream s) {
	if (source != A1) emitMove(A1, source, s);
	s.println(JAL + "_gc_check");
    }

    private static boolean ascii = false;

    /** Alterne o modo de saída para ASCII.
      * @param s o fluxo de saída
     * */
    static void asciiMode(PrintStream s) {
	if (!ascii) {
	    s.print("\t.ascii\t\"");
	    ascii = true;
	}
    }

    /** Alternar o modo de saída para BYTE
      * @param s o fluxo de saída
     * */
    static void byteMode(PrintStream s) {
	if (ascii) {
	    s.println("\"");
	    ascii = false;
	}
    }
    
    /** Emite uma string constante.
      * @param str a string constante
      * @param s o fluxo de saída
     * */
    static void emitStringConstant(String str, PrintStream s) {
	ascii = false;
	
	for (int i = 0; i < str.length(); i++) {
	    char c = str.charAt(i);
	    
	    switch (c) {
	    case '\n':
		asciiMode(s);
		s.print("\\n");
		break;
	    case '\t':
		asciiMode(s);
		s.print("\\t");
		break;
	    case '\\':
		byteMode(s);
		s.println("\t.byte\t" + (byte) '\\');
		break;
	    case '"':
		asciiMode(s);
		s.print("\\\"");
		break;
	    default:
		if (c >= 0x20 && c <= 0x7f) {
		    asciiMode(s);
		    s.print(c);
		} else {
		    byteMode(s);
		    s.println("\t.byte\t" + (byte) c);
		}
	    }
	}
	byteMode(s);
	s.println("\t.byte\t0\t");
    }

    // utilitários próprios
    public static void emitEnterFunc(int size, PrintStream s) {
        emitAddiu(SP, SP, -(size + DEFAULT_OBJFIELDS) * WORD_SIZE, s);
        emitStore(FP, 3 + size, SP, s);
        emitStore(SELF, 2 + size, SP, s);
        emitStore(RA, 1 + size, SP, s);
        emitAddiu(FP, SP, WORD_SIZE, s);
        emitMove(SELF, ACC, s);
    }
    // mantenha o tamanho do AR equilibrado
    public static void emitExitFunc(int size, int tempVarNumber, PrintStream s) {
        emitLoad(FP, 3 + tempVarNumber , SP, s);
        emitLoad(SELF, 2 + tempVarNumber, SP, s);
        emitLoad(RA, 1 + tempVarNumber, SP, s);
        emitAddiu(SP, SP, (3 + size + tempVarNumber) * WORD_SIZE, s);
        emitReturn(s);
    }

    public static void emitAbort(int label, int lineno, StringSymbol filename, String abortRoutine, PrintStream s) {     
        emitBne(ACC, ZERO, label, s);
        emitLoadString(ACC, filename, s);
        emitLoadImm(T1, lineno, s);
        emitJal(abortRoutine, s);
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }
    
    // valor padrão para var não inicializado
    public static void initVar(AbstractSymbol type_decl, PrintStream s) {
        int emptyStr = AbstractTable.stringtable.lookupIndex("");
        int emptyInt = AbstractTable.inttable.lookupIndex("0");
        
        if (TreeConstants.Bool.equals(type_decl)) {
            emitLoadAddress(ACC, BOOLCONST_PREFIX + EMPTYSLOT, s);
        } else if (TreeConstants.Int.equals(type_decl)) {
            emitLoadAddress(ACC, INTCONST_PREFIX + emptyInt, s);
        } else if (TreeConstants.Str.equals(type_decl)) {
            emitLoadAddress(ACC, STRCONST_PREFIX + emptyStr, s);
        } else {
            emitMove(ACC, ZERO, s);
        }
    }

    // Utilitario
    public static void printClassOffset(AbstractSymbol className, AbstractSymbol featureName, int offset) {
        System.out.println("Class: " + className + " Feature Name: " + featureName + " offset: " + offset);
    }   
}
    
    
    

