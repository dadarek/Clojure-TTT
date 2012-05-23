(ns clojure_ttt.minimax-spec
  (:use [speclj.core]))

(defn true-or-false []
  true)

(describe "truthiness"
  (it "tests something"
    (should (true-or-false))))

(run-specs)

