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

(defn won? [board player]
  (winner? (player-squares board player)))

(defn game-over? [board]
  (or (full? board)
      (winner? (player-squares board :x))
      (winner? (player-squares board :o))))

(defn complete-game [board ui]
  (cond
    (full? board) (do
                    (redraw ui board)
                    (announce-tie ui)
                    board)
    (won? board :x) (do
                (redraw ui board)
                (announce-winner ui :x)
                board)
    (won? board :o) (do
                (redraw ui board)
                (announce-winner ui :o)
                 board)))

(defn square-already-taken? [board square]
  (not= nil (nth board (dec square))))

(defn run [x o ui]
  (let [symbol-for #(if (= % x) :x :o)]
    (loop [board empty-board
           current-player x]
      (let [next-player #(if (= x current-player) o x)]
        (if (game-over? board)
          (complete-game board ui)
          (do
            (redraw ui board)
            (announce-next-turn ui (symbol-for current-player))
            (let [next-move (next-move current-player (symbol-for current-player) board)]
              (if (square-already-taken? board next-move)
                (recur board current-player)
                (let [new-board (take-square next-move (symbol-for current-player) board)]
                  (announce-next-move-taken ui (symbol-for current-player) next-move)
                  (recur new-board (next-player)))))))))))

