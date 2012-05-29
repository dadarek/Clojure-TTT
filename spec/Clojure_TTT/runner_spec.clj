(ns clojure_ttt.runner-spec
  (:use [clojure_ttt.runner])
  (:use [clojure_ttt.board_utilities])
  (:use [speclj.core]))

(defn x-moves [board]
  (nth '(1 3 4 8 9) (count (player-squares board :x))))

(defn o-moves [board]
  (nth '(2 5 6 7) (count (player-squares board :o))))

(defrecord MockPlayer [move-maker]
  Player
  (next-move [this board] (move-maker board))
)


(describe "Game Runner"
  (it "Listens to players"
    (should= '(:x :o :x
               :x :o :o
               :o :x :x)
              (run (MockPlayer. x-moves) (MockPlayer. o-moves)))))

(run-specs)
