//- * - modo: java - * -
//
// arquivo: cool-tree.m4
//
// Este arquivo define o AST
//
//////////////////////////////////////////////////////////

import java.util.Enumeration;
import java.io.PrintStream;
import java.util.Vector;
import java.util.*;


/** Define um programa simples phylum */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();

}


/** Define a simple phylum Class_ */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant(ClassTable classTable);
    public abstract void scanFeatures(ClassTable classTable);
}


/** Define a lista phylum Classes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Classes extends ListNode {
    public final static Class elementClass = Class_.class;
    /** Retorna a classe dos elementos desta lista */
    public Class getElementClass() {
        return elementClass;
    }
    protected Classes(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Cria uma lista vazia de "Classes" */
    public Classes(int lineNumber) {
        super(lineNumber);
    }
    /** Acrescenta o elemento "Class_" a esta lista */
    public Classes appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Classes(lineNumber, copyElements());
    }
}


/** Define simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass, boolean firstScan);
    public abstract AbstractSymbol getName();
    public abstract AbstractSymbol getType();
}


/** Define a lista phylum Features
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Features extends ListNode {
    public final static Class elementClass = Feature.class;
    /** Retorna a classe dos elementos desta lista */
    public Class getElementClass() {
        return elementClass;
    }
    protected Features(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Cria uma lista vazia de "Features" */
    public Features(int lineNumber) {
        super(lineNumber);
    }
    /** Acrescenta o elemento "Features" a esta lista */
    public Features appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Features(lineNumber, copyElements());
    }
}


/** Define simple phylum Formal */
abstract class Formal extends TreeNode {
    protected Formal(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass);
    public abstract AbstractSymbol getName();
    public abstract AbstractSymbol getType();
}


/** Define a lista phylum Formals
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Formals extends ListNode {
    public final static Class elementClass = Formal.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Formals(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Cria uma lista "Formals" vazia */
    public Formals(int lineNumber) {
        super(lineNumber);
    }
    /** Anexa elemento "Formal" a esta lista */
    public Formals appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Formals(lineNumber, copyElements());
    }
}


/** Define simple phylum Expressao */
abstract class Expression extends TreeNode {
    protected Expression(int lineNumber) {
        super(lineNumber);
    }
    private AbstractSymbol type = null;                                 
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }

    public abstract Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass);

}


/** Define a lista phylum Expressoes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Expressions extends ListNode {
    public final static Class elementClass = Expression.class;
    /** Retorna a classe dos elementos desta lista */
    public Class getElementClass() {
        return elementClass;
    }
    protected Expressions(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Cria uma lista vazia de "Expressions" */
    public Expressions(int lineNumber) {
        super(lineNumber);
    }
    /** Anexa o elemento "Expression" a esta lista */
    public Expressions appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Expressions(lineNumber, copyElements());
    }
}


/** Define simple phylum Case */
abstract class Case extends TreeNode {
    protected Case(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract AbstractSymbol semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass);
    public abstract AbstractSymbol getType();

}


/** Define a lista phylum Cases
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Cases extends ListNode {
    public final static Class elementClass = Case.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Cases(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Cria uma lista vazia de "Cases" */
    public Cases(int lineNumber) {
        super(lineNumber);
    }
    /** Acrescenta o elemento "Case" a esta lista */
    public Cases appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Cases(lineNumber, copyElements());
    }
}


/** Define AST constructor 'programc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class programc extends Program {
    protected Classes classes;
    /** Cria o nó AST "programc".
    *
    * @param lineNumber a linha no arquivo de origem do qual este nó veio.
    * @param a0 valor inicial para classes
    */
    public programc(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
    }
    public TreeNode copy() {
        return new programc(lineNumber, (Classes)classes.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "programc\n");
        classes.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
            // sm: changed 'n + 1' to 'n + 2' to match changes elsewhere
	    ((Class_)e.nextElement()).dump_with_types(out, n + 2);
        }
    }
    /**Este método é o ponto de entrada para o verificador semântico. Você irá
     precisa completá-lo na atribuição de programação 4.
    <p>
         Seu verificador deve fazer as seguintes duas coisas:
    <ol>
    <li> Verifique se o programa está semanticamente correto
    <li> Decore a árvore de sintaxe abstrata com informações de tipo
         definindo o campo type em cada nó do Expression.
         (veja tree.h)
    </ ol>
    <p>
       Você é livre para fazer primeiro (1) e certifique-se de pegar todos os dados semânticos.
     erros. A parte (2) pode ser feita em um segundo estágio quando você quiser
    para testar o compilador completo.
    */
    public void semant() {
	/* Construtor ClassTable pode fazer alguma análise semântica */
	ClassTable classTable = new ClassTable(classes);
        int typeError = 0;	
        /* Verificar todos os recursos de classe (métodos somente no COOL) para adquirir nomes*/
        for (Enumeration e = classTable.getAllClasses().getElements(); e.hasMoreElements();) {
            ((Class_) e.nextElement()).scanFeatures(classTable);
        }
        
	/* algum código de análise semântica pode ir aqui */
        for (Enumeration e = classes.getElements(); e.hasMoreElements();) {
            ((Class_) e.nextElement()).semant(classTable);
        }

	if (classTable.errors()) {
	    System.err.println("Compilation halted due to static semantic errors.");
	    System.exit(1);
	}
    }

}


/** Definee AST constructor 'class_c'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class class_c extends Class_ {
    protected AbstractSymbol name;
    protected AbstractSymbol parent;
    protected Features features;
    protected AbstractSymbol filename;
    /** ria o nó AST "class_c".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para nome
       * @param a1 valor inicial para pai
       * valor inicial de @param a2 para recursos
       * @param a3 valor inicial para o nome do arquivo
      */
    public class_c(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Features a3, AbstractSymbol a4) {
        super(lineNumber);
        name = a1;
        parent = a2;
        features = a3;
        filename = a4;
    }
    public TreeNode copy() {
        return new class_c(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(parent), (Features)features.copy(), copy_AbstractSymbol(filename));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "class_c\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, parent);
        features.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, filename);
    }

    
    public AbstractSymbol getFilename() { return filename; }
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }

    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_class");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, parent);
        out.print(Utilities.pad(n + 2) + "\"");
        Utilities.printEscapedString(out, filename.getString());
        out.println("\"\n" + Utilities.pad(n + 2) + "(");
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
	    ((Feature)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
    }

    @Override
    public void semant(ClassTable classTable) {
        // os métodos serão usados globalmente onde são despachados como obj.method () (public)
         // Attrs são usados apenas no escopo da classe (privado)
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.enterScope();
        symbolTable.addId(TreeConstants.self, TreeConstants.SELF_TYPE);
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
            Feature f = (Feature) e.nextElement();
            if (f instanceof attr) {
                f.semant(classTable, symbolTable, this, true);
            }
        }
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
            ((Feature) e.nextElement()).semant(classTable, symbolTable, this, false);
        }
        symbolTable.exitScope();
    }

    @Override
    public void scanFeatures(ClassTable classTable) {
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
            Feature f = (Feature) e.nextElement();
                classTable.addFeature(f, this);
        }
    }
}


/** Define AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    protected AbstractSymbol name;
    protected Formals formals;
    protected AbstractSymbol return_type;
    protected Expression expr;

//    protegido AbstractSymbol hiddenType;
    /** Cria o nó AST "método".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para nome
       * @param a1 valor inicial para formals
       * @param valor inicial de a2 para return_type
       * @param a3 valor inicial para expr
      */
    public method(int lineNumber, AbstractSymbol a1, Formals a2, AbstractSymbol a3, Expression a4) {
        super(lineNumber);
        name = a1;
        formals = a2;
        return_type = a3;
        expr = a4;
    }
    public TreeNode copy() {
        return new method(lineNumber, copy_AbstractSymbol(name), (Formals)formals.copy(), copy_AbstractSymbol(return_type), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "method\n");
        dump_AbstractSymbol(out, n+2, name);
        formals.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, return_type);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_method");
        dump_AbstractSymbol(out, n + 2, name);
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
	    ((Formal)e.nextElement()).dump_with_types(out, n + 2);
        }
        dump_AbstractSymbol(out, n + 2, return_type);
	expr.dump_with_types(out, n + 2);
    }
    
    @Override
    public AbstractSymbol getName() {
        return name;
    }
   
    @Override
    public AbstractSymbol getType() {
        return return_type;
    }

    public Formals getFormals() {
        return formals;
    }

    @Override
    public void semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass, boolean firstScan) {
        symbolTable.enterScope();
        
        method originMethod = this;
        AbstractSymbol className = curClass.getName();
        while (!TreeConstants.Object_.equals(className)) {
            method findMethod = (method) classTable.getFeature(name, className, true);
            if (findMethod != null) {
                originMethod = findMethod;
            }
            className = classTable.getParent(className);
        }
   
        if (originMethod.getFormals().getLength() != formals.getLength()) {
            emitErrorIncompatibleNumber(curClass, classTable);
        } else {        
            Enumeration f = originMethod.getFormals().getElements();
            for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
                AbstractSymbol thisType = ((Formal) e.nextElement()).getType();
                AbstractSymbol thatType = ((Formal) f.nextElement()).getType();
                thatType = TreeConstants.No_type.equals(thatType) ? TreeConstants.Object_ : thatType;
                thisType = TreeConstants.No_type.equals(thisType) ? thatType : thisType;
                if (!thisType.equals(thatType)) {
                    emitErrorMethodTypeDifferent(thisType, thatType, curClass, classTable);
                }
            }
        }

        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
            ((Formal)e.nextElement()).semant(classTable, symbolTable, curClass);
        }
        AbstractSymbol exprType = expr.semant(classTable, symbolTable, curClass).get_type(); 
        symbolTable.exitScope();

        if (!classTable.hasType(return_type)) {
            emitErrorUndefinedReturnType(curClass, classTable);
            return_type = TreeConstants.Object_;
        }

        exprType = TreeConstants.No_type.equals(exprType) ? return_type : exprType;
        // se o tipo de retorno for self, o tipo de retorno inferido deverá permanecer não convertido. Caso contrário, converta-se
         // case 1: ID: SELF {self} return SELF_TYPE
         // caso 2: ID: XXX {self} return SELF_TYPE;
         // caso 3: ID: SELF {XXX} errado
         /// A B
         // A return_type B exprType
        if (!TreeConstants.SELF_TYPE.equals(return_type)) {
            exprType = TreeConstants.SELF_TYPE.equals(exprType) ? curClass.getName() : exprType;
        }
 
        if (!classTable.isSubtype(exprType, return_type)) {
            emitErrorMethodTypeNotConform(exprType, curClass, classTable);
        }
    }

    private void emitErrorIncompatibleNumber(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Incompatible number of formal parameters in redefined method " +
                                                 name.toString() + ".");
    }

    private void emitErrorMethodTypeDifferent(AbstractSymbol thisType, AbstractSymbol thatType, 
                                              class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("In redefined method " +
                        name.toString() + ", parameter type " + thisType.toString() +
                        " is different from original type " + thatType.toString());
    }

    private void emitErrorUndefinedReturnType(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Undefined return type " + return_type.toString() +
                                                 " in method " + name.toString() + ".");
    }

    private void emitErrorMethodTypeNotConform(AbstractSymbol exprType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Inferred return type " + 
                   exprType.toString() + " of method " + name.toString() + 
                   " does not conform to declared return type " + return_type.toString() + ".");
    }

}


/** Define AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression init;
    /** Cria o nó AST "attr".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para nome
       * @param a1 valor inicial para type_decl
       * valor inicial de @param a2 para init
      */
    public attr(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        init = a3;
    }
    public TreeNode copy() {
        return new attr(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)init.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "attr\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_attr");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
    }
   
    @Override
    public void semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass, boolean firstScan) {
        int errorCount = 0;
        if (symbolTable.probe(name) != null && firstScan) {
            emitErrorAttrMultiDefined(curClass, classTable);
            errorCount++;
        }
        if (!classTable.hasType(type_decl) && firstScan) {
            emitErrorAttrUndefined(curClass, classTable);
            type_decl = TreeConstants.No_type;
        }
        if (TreeConstants.self.equals(name) & firstScan) {
            emitErrorAttrSelfName(curClass, classTable);
            errorCount++;
        }

        if (errorCount == 0 && firstScan) {
            symbolTable.addId(name, type_decl); 
        }

        if (!firstScan) {
            
            if (classTable.getFeature(name, curClass.getParent(), false) != null) {          
                emitErrorAttrInheritClass(curClass, classTable);  
            }
            if (init instanceof no_expr) {  
                return; 
            }
            AbstractSymbol exprType = init.semant(classTable, symbolTable, curClass).get_type(); 
            exprType = TreeConstants.No_type.equals(exprType) ? type_decl : exprType;
           // if return type is self, inferred return type should keep unconverted. Otherwise conver self

            if (!TreeConstants.SELF_TYPE.equals(type_decl)) {
                exprType = TreeConstants.SELF_TYPE.equals(exprType) ? curClass.getName() : exprType;
            }
            if (!classTable.isSubtype(exprType, type_decl)) {
                emitErrorAttrTypeNotConform(exprType, curClass, classTable);
            }
        }
    }

    private void emitErrorAttrMultiDefined(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Attribute " + name.toString() +" is multiply defined in class.");
    }

    private void emitErrorAttrUndefined(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Class " + type_decl.toString() + 
                                                 " of attribute " + name.toString() + " is undefined.");
    }

    private void emitErrorAttrSelfName(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("'self' cannot be the name of an attribute.");
    }

    private void emitErrorAttrInheritClass(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Attribute " + name.toString() + " is an attribute of an inherited class.");
    }

    private void emitErrorAttrTypeNotConform(AbstractSymbol exprType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Inferred type " + exprType.toString() + 
                               " of initialization of attribute " + name.toString() + 
                               " does not conform to declared type " + type_decl.toString() + ".");
    }

    @Override
    public AbstractSymbol getName() {
        return name;
    }
    
    @Override 
    public AbstractSymbol getType() {
        return type_decl;
    }
        
}


/** Define AST constructor 'formalc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formalc extends Formal {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    /** Cria o nó AST "formalc".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para nome
       * @param a1 valor inicial para type_decl
      */
    public formalc(int lineNumber, AbstractSymbol a1, AbstractSymbol a2) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
    }
    public TreeNode copy() {
        return new formalc(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "formalc\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_formal");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
    }

    public AbstractSymbol getName() {
        return name;
    }
    
    public AbstractSymbol getType() {
        return type_decl;
    }

    @Override
    public void semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        if (TreeConstants.SELF_TYPE.equals(type_decl)) {
            emitErrorFormalSelfType(curClass, classTable);
            symbolTable.addId(name, TreeConstants.No_type);
        } else if (classTable.hasType(type_decl)) {
            if (symbolTable.probe(name) != null) {
                emitErrorFormalMultiDefined(curClass, classTable);
            } else if (TreeConstants.self.equals(name)) {
                emitErrorFormalSelfName(curClass, classTable);
            } else {
                symbolTable.addId(name, type_decl);
            }
        } else {
            emitErrorFormalTypeUndefined(curClass, classTable);
            symbolTable.addId(name, TreeConstants.No_type);
        }
    }

    private void emitErrorFormalSelfType(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Formal parameter " + name.toString() + 
                                                 " cannot have type SELF_TYPE.");
    }

    private void emitErrorFormalMultiDefined(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Formal parameter " + name.toString() + " is multiply defined.");
    }

    private void emitErrorFormalSelfName(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("'self' cannot be the name of a formal parameter.");
    }

    private void emitErrorFormalTypeUndefined(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println(
                   "Class " + curClass.getName().toString() + " of formal parameter " + 
                   name.toString() + " is undefined.");
    }
}


/** Define AST constructor 'branch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression expr;
    /** Cria o nó AST "ramo".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para nome
       * @param a1 valor inicial para type_decl
       * @param a2 valor inicial para expr
      */
    public branch(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        expr = a3;
    }
    public TreeNode copy() {
        return new branch(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "branch\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_branch");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	expr.dump_with_types(out, n + 2);
    }

    @Override
    public AbstractSymbol semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
         AbstractSymbol retType = TreeConstants.Object_;
         if (!classTable.hasType(type_decl)) {
             emitErrorBranchTypeUndefined(curClass, classTable);
         } else if (TreeConstants.SELF_TYPE.equals(type_decl)) {
             emitErrorBranchIdSelfType(curClass, classTable);
         } else {
             symbolTable.enterScope();
             symbolTable.addId(name, type_decl);
             retType = expr.semant(classTable, symbolTable, curClass).get_type();
             symbolTable.exitScope();
             return retType;
         }
         return TreeConstants.No_type;
    }
    
    @Override
    public AbstractSymbol getType() {
        return type_decl;
    }

    private void emitErrorBranchTypeUndefined(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Class " + type_decl.toString() + " of case branch is undefined.");
    }

    private void emitErrorBranchIdSelfType(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Identifier " + name.toString() + " declared with type SELF_TYPE in case branch.");
    }
}


/** Define AST constructor 'assign'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class assign extends Expression {
    protected AbstractSymbol name;
    protected Expression expr;
    /** Cria o nó AST "assign".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para nome
       * @param a1 valor inicial para expr
      */
    public assign(int lineNumber, AbstractSymbol a1, Expression a2) {
        super(lineNumber);
        name = a1;
        expr = a2;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        if (TreeConstants.self.equals(name)) {
            emitErrorAssignSelf(curClass, classTable);
            return set_type(TreeConstants.No_type);
        }

        AbstractSymbol assignType = (AbstractSymbol)symbolTable.lookup(name);
        AbstractSymbol exprType = expr.semant(classTable, symbolTable, curClass).get_type();
        if (assignType == null) {
            Feature attrFeature = classTable.getFeature(name, curClass.getName(), false);
            if (attrFeature != null) {
                assignType = attrFeature.getType();
            } else {
                emitErrorAssignUndeclaredVar(curClass, classTable);
            }
        }
     
        if (assignType != null) {          
            if (!classTable.isSubtype(exprType, assignType)) {
                emitErrorAssignTypeNotConform(exprType, assignType, curClass, classTable);
            } else {
                return set_type(assignType);
            }
        }    
        return set_type(TreeConstants.No_type);        
    }

    private void emitErrorAssignSelf(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Cannot assign to 'self'.");
    }

    private void emitErrorAssignUndeclaredVar(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Assignment to undeclared variable "+ name.toString() +".");
    }

    private void emitErrorAssignTypeNotConform(AbstractSymbol exprType, AbstractSymbol assignType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Type " + exprType.toString() + 
                     " of assigned expression does not conform to declared type " + assignType.toString() +
                     " of identifier " + name.toString() + ".");
    }
}


/** Define AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol type_name;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Cria o nó AST "static_dispatch".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para expr
       * @param a1 valor inicial para type_name
       * @param a2 valor inicial para nome
       * @param a3 valor inicial para reais
      */
    public static_dispatch(int lineNumber, Expression a1, AbstractSymbol a2, AbstractSymbol a3, Expressions a4) {
        super(lineNumber);
        expr = a1;
        type_name = a2;
        name = a3;
        actual = a4;
    }
    public TreeNode copy() {
        return new static_dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(type_name), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "static_dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, type_name);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_static_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, type_name);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol exprType;
        method dispatchMethod = null;

        exprType = expr.semant(classTable, symbolTable, curClass).get_type();
        if (!classTable.isSubtype(exprType, type_name)) {
            emitErrorExprTypeNotConform(exprType, curClass, classTable);
        }
        if (TreeConstants.SELF_TYPE.equals(type_name)) {
            emitErrorDispatchSelf(curClass, classTable);
            type_name = TreeConstants.No_type;
        }
        // procura por métodos despachados em classes ancestrais se type for self
        dispatchMethod = (method) classTable.getFeature(name, type_name, true);
        if (dispatchMethod == null) {
            emitErrorDispatchUndefinedMethod(curClass, classTable);
        } else {
            if (actual.getLength() != dispatchMethod.getFormals().getLength()) {
                emitErrorWrongArguments(curClass, classTable);
            } else {
                Enumeration d = dispatchMethod.getFormals().getElements();
                for (Enumeration e = actual.getElements(); e.hasMoreElements();) {     
                    Formal decl = (Formal) d.nextElement();     
                    AbstractSymbol actualType = ((Expression) e.nextElement())
                                                .semant(classTable, symbolTable, curClass).get_type();            
                    AbstractSymbol evalActualType = 
                                   TreeConstants.SELF_TYPE.equals(actualType) ? curClass.getName() : actualType;
                    if (!classTable.isSubtype(evalActualType, decl.getType())) { 
                        emitErrorMethodTypeNotConform(decl, actualType, curClass, classTable);
                    }
                }
                AbstractSymbol retType = dispatchMethod.getType();
                if (TreeConstants.SELF_TYPE.equals(retType)) {
                    retType = type_name;
                }
                return set_type(retType);
            }
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorExprTypeNotConform(AbstractSymbol exprType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Expression type " + exprType.toString() + 
                 " does not conform to declared static dispatch type " + type_name.toString() + ".");
    }

    private void emitErrorDispatchSelf(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Static dispatch to SELF_TYPE.");
    }

    private void emitErrorDispatchUndefinedMethod(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Dispatch to undefined method " + name.toString() + ".");
    }
   
    private void emitErrorWrongArguments(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Method " + name.toString() + 
                                                 " called with wrong number of arguments.");
    }

    private void emitErrorMethodTypeNotConform(Formal decl, AbstractSymbol actualType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("In call of method " +
                   name.toString() + ", type " + actualType.toString() + " of parameter " +
                   decl.getName().toString() + " does not conform to declared type " + 
                   decl.getType().toString() + ".");
    }
}


/** Define AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for name
      * @param a2 initial value for actual
      */
    public dispatch(int lineNumber, Expression a1, AbstractSymbol a2, Expressions a3) {
        super(lineNumber);
        expr = a1;
        name = a2;
        actual = a3;
    }
    public TreeNode copy() {
        return new dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol exprType;
        method dispatchMethod = null;

        if (expr instanceof no_expr) {
            exprType = TreeConstants.SELF_TYPE;
        } else {
            exprType = expr.semant(classTable, symbolTable, curClass).get_type();
        }
        AbstractSymbol className = TreeConstants.SELF_TYPE.equals(exprType) ? curClass.getName() : exprType;
        dispatchMethod = (method) classTable.getFeature(name, className, true);
        if (dispatchMethod == null) {
            emitErrorDispatchUndefinedMethod(curClass, classTable);
        } else {
            if (actual.getLength() != dispatchMethod.getFormals().getLength()) {
                emitErrorWrongArguments(curClass, classTable);
            } else { 
                Enumeration d = dispatchMethod.getFormals().getElements();
                for (Enumeration e = actual.getElements(); e.hasMoreElements();) {          
                    AbstractSymbol actualType = ((Expression) e.nextElement()).semant(classTable, symbolTable, curClass).get_type();
                    Formal decl = (Formal) d.nextElement();            
                    AbstractSymbol evalActualType = TreeConstants.SELF_TYPE.equals(actualType) ? curClass.getName() : actualType;
                    if (!classTable.isSubtype(evalActualType, decl.getType())) { 
                        emitErrorMethodTypeNotConform(decl, actualType, curClass, classTable);
                    } 
                }
                // se o tipo de método é self, então retorna o tipo expr
                 // ou o tipo de retorno expr é self e dispatchMathod está na classe ancestral
                 // Esta parte é para lidar com new-self-dispatch.cl. Ao despachar métodos com self como tipo de retorno,
                 // o tipo expr do chamador é crucial para determinar o tipo de retorno real do método.
                 // o tipo de retorno declarado é ignorado. Este truque faz o auto tipo se propagar livremente entre as classes com herança
                AbstractSymbol retType = dispatchMethod.getType();
                if (TreeConstants.SELF_TYPE.equals(retType)) { 
                    retType = exprType;
                }
                return set_type(retType);
            }
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorDispatchUndefinedMethod(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Dispatch to undefined method " + name.toString() + ".");
    }

    private void emitErrorWrongArguments(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Method " + name.toString() + 
                                                 " called with wrong number of arguments.");
    }

    private void emitErrorMethodTypeNotConform(Formal decl, AbstractSymbol actualType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("In call of method " +
                   name.toString() + ", type " + actualType.toString() + " of parameter " +
                   decl.getName().toString() + " does not conform to declared type " + 
                   decl.getType().toString() + ".");
    }

}


/** Define AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    protected Expression pred;
    protected Expression then_exp;
    protected Expression else_exp;
    /** Cria o nó AST "cond".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para pred
       * @param a1 valor inicial para then_exp
       * @param valor inicial de a2 para else_exp
      */
    public cond(int lineNumber, Expression a1, Expression a2, Expression a3) {
        super(lineNumber);
        pred = a1;
        then_exp = a2;
        else_exp = a3;
    }
    public TreeNode copy() {
        return new cond(lineNumber, (Expression)pred.copy(), (Expression)then_exp.copy(), (Expression)else_exp.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "cond\n");
        pred.dump(out, n+2);
        then_exp.dump(out, n+2);
        else_exp.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_cond");
	pred.dump_with_types(out, n + 2);
	then_exp.dump_with_types(out, n + 2);
	else_exp.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol predType = pred.semant(classTable, symbolTable, curClass).get_type();
        if (!TreeConstants.Bool.equals(predType)) {
            emitErrorNotBool(curClass, classTable);

        }
        AbstractSymbol thenType = then_exp.semant(classTable, symbolTable, curClass).get_type();
        AbstractSymbol elseType = else_exp.semant(classTable, symbolTable, curClass).get_type();
        return set_type(classTable.getLub(thenType, elseType));
    }

    private void emitErrorNotBool(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Predicate of 'if' does not have type Bool.");
    }

}


/** Define AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    protected Expression pred;
    protected Expression body;
    /** Cria o nó AST "loop".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para pred
       * @param a1 valor inicial para o corpo
      */
    public loop(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        pred = a1;
        body = a2;
    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_loop");
	pred.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol predType = pred.semant(classTable, symbolTable, curClass).get_type();
        if (!TreeConstants.Bool.equals(predType)) {
            emitErrorNotBool(curClass, classTable);
            
        }
        body.semant(classTable, symbolTable, curClass);
        return set_type(TreeConstants.Object_);
    }

    private void emitErrorNotBool(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Loop condition does not have type Bool.");
    }

}


/** Define AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    protected Expression expr;
    protected Cases cases;
    /**Cria o nó AST "typcase".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para expr
       * @param a1 valor inicial para casos
      */
    public typcase(int lineNumber, Expression a1, Cases a2) {
        super(lineNumber);
        expr = a1;
        cases = a2;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "typcase\n");
        expr.dump(out, n+2);
        cases.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_typcase");
	expr.dump_with_types(out, n + 2);
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
	    ((Case)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol exprType = expr.semant(classTable, symbolTable, curClass).get_type();
        AbstractSymbol lubCaseType = TreeConstants.Object_;
        AbstractSymbol retType = TreeConstants.Object_;
        Set<AbstractSymbol> typeSet = new HashSet<AbstractSymbol>();

        exprType = TreeConstants.SELF_TYPE.equals(exprType) ? curClass.getName() : exprType;
        exprType = TreeConstants.No_type.equals(exprType) ? TreeConstants.Object_ : exprType;        

        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
            Case branch = (Case) e.nextElement();
            AbstractSymbol curCaseType = branch.getType();
            AbstractSymbol curRetType = branch.semant(classTable, symbolTable, curClass);
            
            if (typeSet.contains(curCaseType)) {
                emitErrorDuplicateBranch(curCaseType, curClass, classTable);             
                continue;
            } else {
                typeSet.add(curCaseType);
            }
            if (classTable.isSubtype(exprType, curCaseType) && classTable.isSubtype(curCaseType, lubCaseType)) {
                lubCaseType = curCaseType;
                retType = curRetType;
            }   
        }
        return set_type(retType);
    }

    private void emitErrorDuplicateBranch(AbstractSymbol curCaseType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Duplicate branch " + curCaseType.toString() + " in case statement.");
    }

}


/** Define AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    protected Expressions body;
    /** Cria o nó AST "bloco".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para o corpo
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
    }
    public TreeNode copy() {
        return new block(lineNumber, (Expressions)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "block\n");
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_block");
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        Expression lastExpr;
        AbstractSymbol blockType = TreeConstants.No_type;
        if (body.getLength() == 0 ) { return set_type(blockType); }
        for (Enumeration exprs = body.getElements(); exprs.hasMoreElements();) {
            lastExpr = (Expression) exprs.nextElement();
            blockType = lastExpr.semant(classTable, symbolTable, curClass).get_type();       
        }
        return set_type(blockType);
    }

}


/** Define AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    protected AbstractSymbol identifier;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected Expression body;
    /** Cria o nó "let" AST.
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para o identificador
       * @param a1 valor inicial para type_decl
       * valor inicial de @param a2 para init
       * @param a3 valor inicial para o corpo
      */
    public let(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3, Expression a4) {
        super(lineNumber);
        identifier = a1;
        type_decl = a2;
        init = a3;
        body = a4;
    }
    public TreeNode copy() {
        return new let(lineNumber, copy_AbstractSymbol(identifier), copy_AbstractSymbol(type_decl), (Expression)init.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "let\n");
        dump_AbstractSymbol(out, n+2, identifier);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_let");
	dump_AbstractSymbol(out, n + 2, identifier);
	dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        symbolTable.enterScope();
        if (TreeConstants.self.equals(identifier)) { 
            emitErrorBoundSelf(curClass, classTable);
        } else {
            if (!classTable.hasType(type_decl)) {
                emitErrorIdUndefined(curClass, classTable);
                type_decl = TreeConstants.No_type;
            }
            if (!(init instanceof no_expr)) {
                AbstractSymbol initType = init.semant(classTable, symbolTable, curClass).get_type();
                if ((TreeConstants.SELF_TYPE.equals(type_decl) || TreeConstants.SELF_TYPE.equals(initType)) && 
                     !type_decl.equals(initType) && !TreeConstants.No_type.equals(type_decl) && 
                     !TreeConstants.No_type.equals(initType)) {
                    emitErrorInitTypeNotConform(initType, curClass, classTable);
                }
                type_decl = TreeConstants.No_type.equals(type_decl) ? TreeConstants.Object_ : type_decl;
                initType = TreeConstants.No_type.equals(initType) ? type_decl : initType;
                if (!classTable.isSubtype(initType, type_decl)) {
                    emitErrorInitTypeNotConform(initType, curClass, classTable);
                    type_decl = TreeConstants.No_type;
                }
            }
            symbolTable.addId(identifier, type_decl);
        } 
         
        AbstractSymbol letExprType = body.semant(classTable, symbolTable, curClass).get_type(); 
        symbolTable.exitScope();

        return set_type(letExprType); 
    } 

    private void emitErrorBoundSelf(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("'self' cannot be bound in a 'let' expression.");
    }

    private void emitErrorIdUndefined(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Class " + type_decl.toString() + 
                                         " of let-bound identifier " + identifier.toString() + " is undefined.");
    }

    private void emitErrorInitTypeNotConform(AbstractSymbol initType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Inferred type " + initType.toString() + 
                                         " of initialization of " + identifier.toString() + 
                                         " does not conform to identifier's declared type " + type_decl.toString() + ".");
    }          
}


/** Define AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    protected Expression e1;
    protected Expression e2;
    /**Cria o nó "mais" AST.
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
       * @param a1 valor inicial para e2
      */
    public plus(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol leftType, rightType;
        leftType = e1.semant(classTable, symbolTable, curClass).get_type();
        rightType = e2.semant(classTable, symbolTable, curClass).get_type(); 
        if (leftType.equals(TreeConstants.Int) && rightType.equals(TreeConstants.Int)) {
            return set_type(TreeConstants.Int);
        } else {
            emitErrorNonInt(leftType, rightType, curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorNonInt(AbstractSymbol leftType, AbstractSymbol rightType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("non-Int arguments: " + leftType.toString() + 
                                                 " + " + rightType.toString());
    }

}


/** Define AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Cria o nó "sub" AST.
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
       * @param a1 valor inicial para e
      */
    public sub(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol leftType, rightType;
        leftType = e1.semant(classTable, symbolTable, curClass).get_type();
        rightType = e2.semant(classTable, symbolTable, curClass).get_type(); 
        if (leftType.equals(TreeConstants.Int) && rightType.equals(TreeConstants.Int)) {
            return set_type(TreeConstants.Int);
        } else {
            emitErrorNonInt(leftType, rightType, curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorNonInt(AbstractSymbol leftType, AbstractSymbol rightType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("non-Int arguments: " + leftType.toString() + 
                                                 " + " + rightType.toString());
    }

}


/** Define AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Cria o nó AST "mul".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
       * @param a1 valor inicial para e2
      */
    public mul(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol leftType, rightType;
        leftType = e1.semant(classTable, symbolTable, curClass).get_type();
        rightType = e2.semant(classTable, symbolTable, curClass).get_type(); 
        if (leftType.equals(TreeConstants.Int) && rightType.equals(TreeConstants.Int)) {
            return set_type(TreeConstants.Int);
        } else {
            emitErrorNonInt(leftType, rightType, curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorNonInt(AbstractSymbol leftType, AbstractSymbol rightType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("non-Int arguments: " + leftType.toString() + 
                                                 " + " + rightType.toString());
    }

}


/** Define AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Cria o nó AST "divide".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
       * @param a1 valor inicial para e2
      */
    public divide(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol leftType, rightType;
        leftType = e1.semant(classTable, symbolTable, curClass).get_type();
        rightType = e2.semant(classTable, symbolTable, curClass).get_type(); 
        if (leftType.equals(TreeConstants.Int) && rightType.equals(TreeConstants.Int)) {
            return set_type(TreeConstants.Int);
        } else {
            emitErrorNonInt(leftType, rightType, curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorNonInt(AbstractSymbol leftType, AbstractSymbol rightType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("non-Int arguments: " + leftType.toString() + 
                                                 " + " + rightType.toString());
    }
}


/** Define AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    protected Expression e1;
    /** Cria o nó AST "neg".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol exprType = e1.semant(classTable, symbolTable, curClass).get_type();
        if (TreeConstants.Int.equals(exprType)) {
            return set_type(TreeConstants.Int);
        } else {
            emitErrorNonInt(exprType, curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorNonInt(AbstractSymbol exprType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Argument of '~' has type " +
                                                 exprType.toString() + " instead of Int.");
    }
}


/** Define AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Cria o nó "lt" AST.
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
       * @param a1 valor inicial para e2
      */
    public lt(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol leftType = e1.semant(classTable, symbolTable, curClass).get_type();
        AbstractSymbol rightType = e2.semant(classTable, symbolTable, curClass).get_type();

        if (TreeConstants.Int.equals(leftType) && TreeConstants.Int.equals(rightType)) {
            return set_type(TreeConstants.Bool);
        } else {
            emitErrorNonInt(leftType, rightType, curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorNonInt(AbstractSymbol leftType, AbstractSymbol rightType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("non-Int arguments: " + leftType.toString() + 
                                                 " < " + rightType.toString());
    }
}


/** Define AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Cria o nó AST "eq".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
       * @param a1 valor inicial para e2
      */
    public eq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol leftType = e1.semant(classTable, symbolTable, curClass).get_type();
        AbstractSymbol rightType = e2.semant(classTable, symbolTable, curClass).get_type();
        if (TreeConstants.Int.equals(leftType) || TreeConstants.Bool.equals(leftType) || TreeConstants.Str.equals(leftType)) {
            if (!leftType.equals(rightType)) {
                emitErrorIllegalCompare(curClass, classTable);
                return set_type(TreeConstants.No_type);
            }
        }
        return set_type(TreeConstants.Bool);
    }

    private void emitErrorIllegalCompare(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Illegal comparison with a basic type.");
    }
}


/** Define AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Cria o nó AST "leq".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
       * @param a1 valor inicial para e2
      */
    public leq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol leftType = e1.semant(classTable, symbolTable, curClass).get_type();
        AbstractSymbol rightType = e2.semant(classTable, symbolTable, curClass).get_type();

        if (TreeConstants.Int.equals(leftType) && TreeConstants.Int.equals(rightType)) {
            return set_type(TreeConstants.Bool);
        } else {
            emitErrorNonInt(leftType, rightType, curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorNonInt(AbstractSymbol leftType, AbstractSymbol rightType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("non-Int arguments: " + leftType.toString() + 
                                                 " <= " + rightType.toString());
    }
}


/** Define AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    protected Expression e1;
    /** Cria o nó AST "comp".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        AbstractSymbol exprType = e1.semant(classTable, symbolTable, curClass).get_type();
        if (TreeConstants.Bool.equals(exprType)) {
            return set_type(TreeConstants.Bool);
        } else {
            emitErrorNotBool(exprType, curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorNotBool(AbstractSymbol exprType, class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Argument of 'not' has type " + exprType.toString() + 
                                                 " instead of Bool.");
    }
}


/** Define AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    protected AbstractSymbol token;
    /** Cria o nó AST "int_const".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para token
      */
    public int_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_int");
	dump_AbstractSymbol(out, n + 2, token);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        return set_type(TreeConstants.Int);
    }

}


/** Define AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    protected Boolean val;
    /** Cria o nó AST "bool_const".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para val
      */
    public bool_const(int lineNumber, Boolean a1) {
        super(lineNumber);
        val = a1;
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "bool_const\n");
        dump_Boolean(out, n+2, val);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_bool");
	dump_Boolean(out, n + 2, val);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        return set_type(TreeConstants.Bool);
    }

}


/** Define AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    protected AbstractSymbol token;
    /** Cria o nó AST "string_const".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para token
      */
    public string_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "string_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
	out.print(Utilities.pad(n + 2) + "\"");
	Utilities.printEscapedString(out, token.getString());
	out.println("\"");
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        return set_type(TreeConstants.Str);
    }

}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    protected AbstractSymbol type_name;
    /** Cria o nó AST "new_".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_new");
	dump_AbstractSymbol(out, n + 2, type_name);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        if (classTable.hasType(type_name)) {
            return set_type(type_name);
        }
        emitErrorClassUndefined(curClass, classTable);
        return set_type(TreeConstants.No_type);     
    }

    private void emitErrorClassUndefined(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("'new' used with undefined class " + type_name.toString() + ".");
    }
}


/** Define AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    protected Expression e1;
    /** Cria o nó AST "isvoid".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        e1.semant(classTable, symbolTable, curClass);
        return set_type(TreeConstants.Bool);
    }

}


/** Define AST constructor 'no_expr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class no_expr extends Expression {
    /** Cria o nó AST "no_expr".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.    */
    public no_expr(int lineNumber) {
        super(lineNumber);
    }
    public TreeNode copy() {
        return new no_expr(lineNumber);
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "no_expr\n");
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_no_expr");
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) {
        return set_type(TreeConstants.No_type);
    }

}


/** Define AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    protected AbstractSymbol name;
    /** Cria o nó AST "objeto".
       *
       * @param lineNumber a linha no arquivo de origem do qual este nó veio.
       * @param a0 valor inicial para nome
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }

    @Override
    public Expression semant(ClassTable classTable, SymbolTable symbolTable, class_c curClass) { 
        if (symbolTable.lookup(name) != null) {
            return set_type((AbstractSymbol) symbolTable.lookup(name));
        } else {
            // check ancestor classes if cannot find in outer scope
            attr attrObject = (attr) classTable.getFeature(name, curClass.getName(), false);
            if (attrObject != null) {
                return set_type(attrObject.getType());
            }
            emitErrorIdUndefined(curClass, classTable);
        }
        return set_type(TreeConstants.No_type);
    }

    private void emitErrorIdUndefined(class_c curClass, ClassTable classTable) {
        classTable.semantError(curClass).println("Undeclared identifier " + name.toString() + ".");
    }
}


