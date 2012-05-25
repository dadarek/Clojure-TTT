(ns clojure_ttt.game-spec
  (:use [clojure_ttt.game])
  (:use [speclj.core]))

(describe "Game"
  (it "Compiles"
    (should= false (skskgo))))

(run-specs)

