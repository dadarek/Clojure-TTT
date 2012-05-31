(ns clojure_ttt.computer-spec
  (:use clojure_ttt.runner)
  (:use clojure_ttt.computer)
  (:use clojure_ttt.generic_minimax)
  (:use [clojure_ttt.minimax_injections :as mmi])
  (:import [clojure_ttt.computer Computer])
  (:use [speclj.core]))

(def computer (Computer. :o))

(describe "Computer"
  (it "passes board into minimax"
    (let [board '(:x  :o nil
                  nil :x nil
                  nil :o nil)]
      (with-redefs [clojure_ttt.minimax/next-move (fn [_ board]
                            (throw (Exception. (str board))))]
        (should-throw
          Exception (str board)
          (next-move computer board)))))

  (it "passes in its symbol to minimax"
    (with-redefs [clojure_ttt.minimax/next-move (fn [sym _]
                          (throw (Exception. (str sym))))]
      (should-throw
        Exception (str :o)
        (next-move computer nil)))))

(run-specs)
