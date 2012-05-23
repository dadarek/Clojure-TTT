(ns clojure_ttt.minimax-spec
  (:use [clojure_ttt.minimax])
  (:use [speclj.core]))

(describe "Minimax"
  (it "Dummy tests"
    (should= 6 (* 3 2)))
)

(run-specs)

