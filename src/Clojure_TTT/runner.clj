(ns clojure_ttt.runner
  (:use [clojure_ttt.board_utilities])
  (:use [clojure_ttt.rules]))

(defprotocol Player
  (next-move [player board]))

(def empty-board '(nil nil nil nil nil nil nil nil nil))

(defn run [x o]
  (loop [board empty-board
         player :x]
    (let [x-won? (winner? (player-squares board :x))
          o-won? (winner? (player-squares board :o))
          square-taken? #(not= nil (nth board (dec %)))]

      (if (or (full? board) x-won? o-won?)
        board
        (let [current-player (if (= :x player) x o)
              next-move (next-move current-player board)]
          (if (square-taken? next-move)
            (recur board player)
            (let [new-board (take-square next-move {:board board :player player})
                  new-player (if (= :x player) :o :x)]
          (recur new-board new-player))))))))

