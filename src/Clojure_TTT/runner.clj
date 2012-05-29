(ns clojure_ttt.runner
  (:use [clojure_ttt.board_utilities])
  (:use [clojure_ttt.rules]))

(defprotocol Player
  (next-move [p board]))

(defn next-player [context]
  (if (= :x (:player context)) :o :x))

(defn run [x o]
  (loop [context {:board '(nil nil nil nil nil nil nil nil nil) :player :x}]
    (let [board (:board context)
          x-won (winner? (player-squares board :x))
          o-won (winner? (player-squares board :o))]
      (if (or (full? board) x-won o-won)
        board
        (let [current-player (if (= :x (:player context)) x o)
              next-move (next-move current-player board)
              new-board (take-square next-move context)
              new-player (next-player context)
              new-context {:board new-board :player new-player} ]
          (recur new-context))))))

