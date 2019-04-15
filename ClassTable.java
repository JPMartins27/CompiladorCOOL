import java.io.PrintStream;
import java.util.*;

/ ** esta classe pode ser usada para conter as informações semânticas como
  * o gráfico de herança. voce pode usá lo ou não como você preferir é só
  * aqui fornecer um contêiner para os métodos fornecidos. */
class ClassTable {
    private AbstractSymbol filename;
    private int semantErrors;
    private PrintStream errorStream;

    private Map<AbstractSymbol, class_c> basicClassMap;
    private Map<AbstractSymbol, class_c> classTableMap;
    private Map<AbstractSymbol, Features> featureTableMap;

    / ** cria estruturas de dados representando classes cool basica objeto,
      * io, in t, bool, string).  note: como é este método não
      * faça qualquer coisa útil; você precisará editá-lo para fazer se fazer o que
      * você quer
      * * /
    private void installBasicClasses() {
	AbstractSymbol filename 
	    = AbstractTable.stringtable.addString("<basic class>");
	
	// O seguinte demonstra como criar árvores de análise abstrata para
    // refere-se a classes Cool básicas. Não há necessidade de método
    // bodies - estes já estão embutidos no sistema de tempo de execução.

    // IMPORTANTE: Os resultados das seguintes expressões são
    // armazenado em variáveis locais. Você vai querer fazer alguma coisa
    // com essas variáveis no final desse método para fazer isso
    // código significativo.

    // A classe Object não possui uma classe pai. Seus métodos são
    // cool_abort (): o objeto anula o programa
    // type_name (): Str retorna uma representação de string
    // do nome da classe
    // copy (): SELF_TYPE retorna uma cópia do objeto

	class_c Object_class = 
	    new class_c(0, 
		       TreeConstants.Object_, 
		       TreeConstants.No_class,
		       new Features(0)
			   .appendElement(new method(0, 
					      TreeConstants.cool_abort, 
					      new Formals(0), 
					      TreeConstants.Object_, 
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.type_name,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.copy,
					      new Formals(0),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0))),
		       filename);
	
	  // A classe IO herda de Object. Seus métodos são
     // out_string (Str): SELF_TYPE escreve uma string na saída
     // out_int (Int): SELF_TYPE "um int" ""
    // in_string (): Str lê uma string da entrada
    // in_int (): Int "um int" ""

	class_c IO_class = 
	    new class_c(0,
		       TreeConstants.IO,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new method(0,
					      TreeConstants.out_string,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Str)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.out_int,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_string,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_int,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0))),
		       filename);

	// A classe Int não possui métodos e apenas um único atributo, o
    // "val" para o inteiro.

	class_c Int_class = 
	    new class_c(0,
		       TreeConstants.Int,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	// Bool também tem apenas o slot "val".
	class_c Bool_class = 
	    new class_c(0,
		       TreeConstants.Bool,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	/ A classe Str possui vários slots e operações:
    // val o comprimento da string
    // str_field a própria string
    // length (): Int retorna o comprimento da string
    // concat (arg: Str): Str realiza concatenação de strings
    // substr (arg: Int, arg2: Int): seleção de str substring

	class_c Str_class =
	    new class_c(0,
		       TreeConstants.Str,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.Int,
					    new no_expr(0)))
			   .appendElement(new attr(0,
					    TreeConstants.str_field,
					    TreeConstants.prim_slot,
					    new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.length,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.concat,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg, 
								     TreeConstants.Str)),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.substr,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int))
						  .appendElement(new formalc(0,
								     TreeConstants.arg2,
								     TreeConstants.Int)),
					      TreeConstants.Str,
					      new no_expr(0))),
		       filename);

        
        class_c Self_class = 
            new class_c (0, TreeConstants.SELF_TYPE, 
                            TreeConstants.Object_,
                            new Features(0),
                            filename
            );


	/* Faça algo com Object_class, IO_class, Int_class, Bool_class e Str_class aqui */
        this.filename = filename;
        basicClassMap.put(TreeConstants.Object_, Object_class);
        basicClassMap.put(TreeConstants.IO, IO_class);
        basicClassMap.put(TreeConstants.Int, Int_class);
        basicClassMap.put(TreeConstants.Bool, Bool_class);
        basicClassMap.put(TreeConstants.Str, Str_class); 
        basicClassMap.put(TreeConstants.SELF_TYPE, Self_class); 
     
        classTableMap.putAll(basicClassMap);
    }

    public ClassTable(Classes cls) {
	semantErrors = 0;
	errorStream = System.err;
        basicClassMap = new HashMap<AbstractSymbol, class_c>();	
        classTableMap = new HashMap<AbstractSymbol, class_c>();
        featureTableMap = new HashMap<AbstractSymbol, Features>();
	
        installBasicClasses();
        classTableMap = checkClasses(cls);
        checkCycle();
    }

    public boolean hasType(AbstractSymbol type) {
        return basicClassMap.containsKey(type) || classTableMap.containsKey(type);
    }

    public boolean isSubtype(AbstractSymbol sub, AbstractSymbol ancestor) {
        assert(hasType(sub) && hasType(ancestor));
        if (TreeConstants.SELF_TYPE.equals(sub) || TreeConstants.SELF_TYPE.equals(ancestor)) {
            return sub.equals(ancestor);
        }
        if (ancestor.equals(sub) || ancestor.equals(TreeConstants.Object_)) { return true; }
        if (basicClassMap.containsKey(sub)) { return false; }

        while (!sub.equals(TreeConstants.Object_)) {
            if (sub.equals(ancestor)) { return true; }
            sub = classTableMap.get(sub).parent;
        }
        return false; 
    }
   // obtém o recurso de curClass ou seu ancestral mais próximo
    public Feature getFeature(AbstractSymbol featureName, AbstractSymbol className, boolean isMethod) {
        if (classTableMap.containsKey(className)) {
            AbstractSymbol pt = className; 
            while (!TreeConstants.No_class.equals(pt)) {
                Features features = featureTableMap.get(pt);
                if (features != null) {
                    for (Enumeration e = features.getElements(); e.hasMoreElements();) {
                        Feature feature = (Feature) e.nextElement();
                        if (feature.getName().equals(featureName)) {
                            if ((feature instanceof method && isMethod) ||
                                (feature instanceof attr && !isMethod)) {
                                return feature;
                            }
                        }
                    }
                }
                pt = classTableMap.get(pt).getParent();
            }
        }
        return null; 
    }

    public void addFeature(Feature feature, class_c curClass) {
        Features features;
        if (!featureTableMap.containsKey(curClass.getName())) {
            features = new Features(0);
        } else {
            features = featureTableMap.get(curClass.getName());
        }
        featureTableMap.put(curClass.getName(), features.appendElement(feature));
    }

    public void dumpFeatures() {
        for (AbstractSymbol sym : featureTableMap.keySet()) {
            for (Enumeration e = featureTableMap.get(sym).getElements(); e.hasMoreElements();) {
                System.out.print(sym.toString() + "   ");
                System.out.println(((Feature)e.nextElement()).getName());
            }
        }
    }

    public AbstractSymbol getParent(AbstractSymbol className) {
        if (classTableMap.containsKey(className)) {
            return classTableMap.get(className).parent;
        }
        return TreeConstants.Object_;
    }

    public Classes getAllClasses() {
        Classes classes = new Classes(0);
        for (AbstractSymbol className : classTableMap.keySet()) {
            classes.addElement(classTableMap.get(className));
        }
        return classes;
    }

    public AbstractSymbol getLub(AbstractSymbol typeA, AbstractSymbol typeB) {
        if (typeA.equals(typeB) || isSubtype(typeB, typeA)) { return typeA; }
        if (isSubtype(typeA, typeB)) { return typeB; }
        return getLub(getParent(typeA), getParent(typeB));
    }

    private void checkCycle() {
        Set <AbstractSymbol> visited = new HashSet<AbstractSymbol>();
        Set <AbstractSymbol> undefined = new HashSet<AbstractSymbol>();
        for (AbstractSymbol key : classTableMap.keySet()) {
            class_c curClass = classTableMap.get(key);
            visited.clear();
            visited.add(curClass.name);
            while (!TreeConstants.Object_.equals(curClass.name) &&
                   !TreeConstants.IO.equals(curClass.name)) {
                if (!classTableMap.containsKey(curClass.parent)) {
                    if (!undefined.contains(curClass.parent)) {
                        undefined.add(curClass.parent);
                        emitErrorInheritUndefinedClass(curClass);
                    }
                    break;
                } else if (visited.contains(curClass.parent)) {
                    emitErrorInheritanceCycle(curClass);
                    break;
                } else {
                    curClass = classTableMap.get(curClass.parent);
                    visited.add(curClass.name);
                }
            }     
        }
    }

    private void emitErrorInheritUndefinedClass(class_c curClass) {
        semantError(curClass).println("Class " + curClass.name.toString() + 
                                      " inherits from an undefined class " +
                                      curClass.parent.toString() + ".");
    }

    private void emitErrorInheritanceCycle(class_c curClass) {
        semantError(curClass).println("Class " + curClass.parent.toString() +
                                      ", or an ancestor of " + curClass.parent.toString() +
                                      ", is involved in an inheritance cycle.");
    }

    // obtém o recurso de curClass ou seu ancestral mais próximo
    private Map<AbstractSymbol, class_c> checkClasses(Classes cls) {
        class_c curClass;
	for (Enumeration e = cls.getElements(); e.hasMoreElements();) {
            curClass = (class_c) e.nextElement();
            AbstractSymbol parent = curClass.parent;
            if (basicClassMap.containsKey(curClass.name)) {
                emitErrorRedefineBasicClass(curClass);
            }
            if (parent.equals(TreeConstants.Int) || 
                parent.equals(TreeConstants.Bool) ||
                parent.equals(TreeConstants.Str) ||
                parent.equals(TreeConstants.SELF_TYPE)) {
                emitErrorCannotInheritClass(curClass);
            }
            
            if (classTableMap.containsKey(curClass.name)) {
                emitErrorClassAlreadyDefined(curClass);
            }
            classTableMap.put(curClass.name, curClass);
        }
        if (!classTableMap.containsKey(TreeConstants.Main)) {
            emitErrorMainNotDefined();
        }
        return classTableMap;
    }

    private void emitErrorRedefineBasicClass(class_c curClass) {
        semantError(curClass).println("Redefinition of basic class " + curClass.name.toString() + ".");
    }

    private void emitErrorCannotInheritClass(class_c curClass) {
        semantError(curClass).println("Class " + curClass.name.toString() + " cannot inherit class " +
                                      curClass.parent.toString() + ".");
    }

    private void emitErrorClassAlreadyDefined(class_c curClass) {
        semantError(curClass).println("Class " + curClass.name.toString() + " was previously defined.");
    }

    private void emitErrorMainNotDefined() {
        semantError().println("Class Main is not defined.");
    }
    
    /** Imprime o número da linha e o nome do arquivo da classe dada.
    *
    * Também aumenta a contagem de erros semânticos.
    *
    * @param c a classe
    * @retorna um fluxo de impressão para o qual o restante da mensagem de erro é
    * para ser impresso.
    *
    * */
    public PrintStream semantError(class_c c) {
	return semantError(c.getFilename(), c);
    }

    /**Imprime o nome do arquivo e o número da linha do nó da árvore fornecido.
     *
     * Também aumenta a contagem de erros semânticos.
     *
     * @param filename o nome do arquivo
     * @param o nó da árvore
     * @retorna um fluxo de impressão para o qual o restante da mensagem de erro é
     * para ser impresso.
     *
     * */
    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
	errorStream.print(filename + ":" + t.getLineNumber() + ": ");
	return semantError();
    }

    /** Incrementa a contagem de erros semânticos e retorna o fluxo de impressão para
    * mensagens de erro.
    *
    * @retorna um fluxo de impressão no qual a mensagem de erro é
    * para ser impresso.
    *
    **/
    public PrintStream semantError() {
	semantErrors++;
	return errorStream;
    }

    /** Retorna true se houver algum erro semântico estático. */
    public boolean errors() {
	return semantErrors != 0;
    }

    public class_c getClass(AbstractSymbol className) {  
        return classTableMap.get(className);
    }
}
			  
    
