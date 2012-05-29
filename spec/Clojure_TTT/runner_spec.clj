(ns clojure_ttt.runner-spec
  (:use [clojure_ttt.runner])
  (:use [speclj.core]))

(describe "Game Runner"
  (it "Compiles"
    (should= true (hi))))

(run-specs)
