(ns clojure_ttt.runner-spec
  (:use [clojure_ttt.runner])
  (:use [clojure_ttt.board_utilities])
  (:use [speclj.core]))

(defrecord MockPlayer [moves player]
  Player
  (next-move [this board]
    (nth moves (count (player-squares board player)))))


(describe "Game Runner"
  (it "Listens to players"
    (should= '(:x :o :x
               :x :o :o
               :o :x :x)
              (run (MockPlayer. '(1 3 4 8 9) :x)
                   (MockPlayer. '(2 5 6 7) :o))))

  (it "Stops when somebody wins"
    (should= '(:x  :x  :x
               :o  :o  nil
               nil nil nil)
              (run (MockPlayer. '(1 2 3) :x)
                   (MockPlayer. '(4 5) :o)))))

(run-specs)
