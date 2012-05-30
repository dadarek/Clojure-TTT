(ns clojure_ttt.human_player-spec
  (:use clojure_ttt.runner)
  (:use clojure_ttt.human_player)
  (:import [clojure_ttt.human_player HumanPlayer])
  (:use [speclj.core]))

(def player (HumanPlayer. :o))

(describe "Human Player"
  (it "Compiles"
    (should= 5 (next-move player nil))))

(run-specs)
