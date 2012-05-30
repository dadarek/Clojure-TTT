(ns clojure_ttt.computer-spec
  (:use clojure_ttt.runner)
  (:use clojure_ttt.computer)
  (:use clojure_ttt.generic_minimax)
  (:import [clojure_ttt.computer Computer])
  (:use [speclj.core]))

(describe "Computer"
  (it "Returns best scoring moving from minimax"
    (with-redefs [score (fn [&] '(1 3 2))]
      (should= 2 (next-move (Computer. :o) nil)))))

(run-specs)
