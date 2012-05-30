(ns clojure_ttt.computer-spec
  (:use clojure_ttt.runner)
  (:import [clojure_ttt.computer Computer])
  (:use [speclj.core]))

(describe "Computer"
  (it "Prints next move"
    (doall (println (next-move (Computer. :bb) nil)))))

(run-specs)
