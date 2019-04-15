
(*
 * execute "coolc bad.cl" para ver as mensagens de erro que o coolc parser
  * gera
  *
  * execute "parsetest <bad.cl" para ver as mensagens de erro que seu analisador
  * gera
 *)

(* no error *)
class A {
};

(* error:  b is not a type identifier *)
Class b inherits A {
};

(* error:  a is not a type identifier *)
Class C inherits a {
};

(* error:  keyword inherits is misspelled *)
Class D inherts A {
};

(* error:  closing brace is missing *)
Class E inherits A {
;

