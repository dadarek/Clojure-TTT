(ns clojure_ttt.human_player-spec
  (:use clojure_ttt.runner)
  (:use clojure_ttt.human_player)
  (:import [clojure_ttt.human_player HumanPlayer])
  (:use [speclj.core]))


(defrecord DummyUI [move]
  NextMoveUI
  (get-next-move [this] move))


(describe "Human Player"
  (it "Listens to UI"
    (should= 5 
      (next-move (HumanPlayer. (DummyUI. 5)) nil)))

  (it "Doesn't simply return 5"
    (should= 8 
      (next-move (HumanPlayer. (DummyUI. 8)) nil))))

(run-specs)
