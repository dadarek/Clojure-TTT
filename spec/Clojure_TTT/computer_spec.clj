(ns clojure_ttt.computer-spec
  (:use clojure_ttt.runner)
  (:use clojure_ttt.computer)
  (:use clojure_ttt.generic_minimax)
  (:use [clojure_ttt.minimax_injections :as mmi])
  (:import [clojure_ttt.computer Computer])
  (:use [speclj.core]))

(describe "Computer"
  (it "Returns best scoring moving from minimax"
    (with-redefs [score (fn [& args] '(1 3 2))]
      (should= 2 (next-move (Computer. :o) nil))))

  (it "Returns first square if several have high score"
    (with-redefs [score (fn [& args] '(3 3 2))]
      (should= 1 (next-move (Computer. :o) nil))))

  (it "passes in all open moves to minimax"
    (let [board '(:x  :o nil
                  nil :x nil
                  nil :o nil)]
      (with-redefs [score (fn [moves & args]
                            (throw (Exception. (str moves))))]
        (should-throw
          Exception "#{3 4 6 7 9}"
          (next-move (Computer. :o) board)))))

  (it "passes in its symbol to minimax"
    (with-redefs [score (fn [moves player & args]
                          (throw (Exception. (str player))))]
      (should-throw
        Exception ":john"
        (next-move (Computer. :john) nil))))

  (it "injects correct functions to minimax"
    (with-redefs
      [score
        (fn [moves player wins? ties? next-context]
          (if (not= wins? mmi/wins?) (throw (Exception. "Invalid wins? fn.")))
          (if (not= ties? mmi/ties?) (throw (Exception. "Invalid ties? fn.")))
          (if (not= next-context mmi/next-context) (throw (Exception. "Invalid next-context fn.")))
          (throw (Exception. "All Good")))]
      (should-throw
        Exception "All Good"
        (next-move (Computer. :john) nil)))))

(run-specs)
