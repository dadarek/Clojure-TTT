(ns clojure_ttt.computer-spec
  (:use clojure_ttt.runner)
  (:use clojure_ttt.computer)
  (:import [clojure_ttt.computer Computer])
  (:use [speclj.core]))

(def computer (Computer.))

(describe "Computer"
  (it "passes board into minimax"
    (let [board '(:x  :o nil
                  nil :x nil
                  nil :o nil)]
      (with-redefs [clojure_ttt.minimax/next-move (fn [_ board]
                            (throw (Exception. (str board))))]
        (should-throw
          Exception (str board)
          (next-move computer nil board)))))

  (it "passes in its symbol to minimax"
    (with-redefs [clojure_ttt.minimax/next-move (fn [sym _]
                          (throw (Exception. (str sym))))]
      (should-throw
        Exception (str :hello)
        (next-move computer :hello nil)))))

(run-specs)
