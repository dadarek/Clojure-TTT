(ns clojure_ttt.computer-spec
  (:use clojure_ttt.runner)
  (:use clojure_ttt.computer)
  (:use clojure_ttt.generic_minimax)
  (:import [clojure_ttt.computer Computer])
  (:use [speclj.core]))

(describe "Computer"
  (it "Returns best scoring moving from minimax"
    (with-redefs [score (fn [& args] '(1 3 2))]
      (should= 2 (next-move (Computer. :o) nil))))

  (it "Returns first square if several have high score"
    (with-redefs [score (fn [& args] '(3 3 2))]
      (should= 1 (next-move (Computer. :o) nil))))

  (it "passes in all open moves"
    (let [board '(:x  :o nil
                  nil :x nil
                  nil :o nil)]
      (with-redefs [score (fn [moves & args]
                            (throw (Exception. (str moves))))]
        (should-throw
          Exception "#{3 4 6 7 9}"
          (next-move (Computer. :o) board)))))

  (it "passes in its symbol"
    (with-redefs [score (fn [moves player & args]
                          (throw (Exception. (str player))))]
      (should-throw
        Exception ":john"
        (next-move (Computer. :john) nil))))
)

(run-specs)
