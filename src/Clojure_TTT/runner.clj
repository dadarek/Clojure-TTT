(ns clojure_ttt.runner
  (:use [clojure_ttt.board_utilities]))

(defprotocol Player
  (next-move [p board])
)

(defn next-player [context]
  (if (= :x (:player context)) :o :x))

(defn run [x o]
  (loop [context {:board '(nil nil nil nil nil nil nil nil nil) :player :x}]
    (if (full? (:board context))
      (:board context)
      (let [current-player (if (= :x (:player context)) x o)
            next-move (next-move current-player (:board context))
            new-board (take-square next-move context)
            new-player (next-player context)
            new-context {:board new-board :player new-player} ]
        (recur new-context)))))

