(ns clojure_ttt.ui_player-spec
  (:use clojure_ttt.runner)
  (:use clojure_ttt.ui_player)
  (:import [clojure_ttt.ui_player UIPlayer])
  (:use [speclj.core]))

(def player (UIPlayer. :o))

(describe "UI Player"
  (it "Compiles"
    (should= 5 (next-move player nil))))

(run-specs)
