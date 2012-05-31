(ns clojure_ttt.runner
  (:use [clojure_ttt.board_utilities])
  (:use [clojure_ttt.rules]))

(defprotocol RunnerUI
  (redraw [this board])
  (announce-next-turn [this player])
  (announce-next-move-taken [this player move])
  (announce-tie [this])
  (announce-winner [this player]))

(defprotocol Player
  (next-move [player x-or-o board]))

(def empty-board '(nil nil nil nil nil nil nil nil nil))

(defn run [x o ui]
  (let [symbol-for #(if (= % x) :x :o)]
    (loop [board empty-board
           current-player x]
      (let [won? #(winner? (player-squares board %))
            square-taken? #(not= nil (nth board (dec %)))
            next-player #(if (= x current-player) o x)]
        (cond
          (full? board) (do
                          (redraw ui board)
                          (announce-tie ui)
                          board)
          (won? :x) (do
                      (redraw ui board)
                      (announce-winner ui :x)
                      board)
          (won? :o) (do
                      (redraw ui board)
                      (announce-winner ui :o)
                       board)
          :else (do
            (redraw ui board)
            (announce-next-turn ui (symbol-for current-player))
            (let [next-move (next-move current-player (symbol-for current-player) board)]
              (if (square-taken? next-move)
                (recur board current-player)
                (let [new-board (take-square next-move {:board board :player (symbol-for current-player)})]
                  (announce-next-move-taken ui (symbol-for current-player) next-move)
                  (recur new-board (next-player)))))))))))

