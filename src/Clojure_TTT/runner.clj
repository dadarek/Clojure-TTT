(ns clojure_ttt.runner
  (:use [clojure_ttt.board_utilities])
  (:use [clojure_ttt.rules]))

(defprotocol RunnerUI
  (redraw [this board]))

(defprotocol Player
  (next-move [player board]))

(def empty-board '(nil nil nil nil nil nil nil nil nil))

(defn run [x o ui]
  (let [symbol-for #(if (= % x) :x :o)]
    (loop [board empty-board
           current-player x]
      (let [won? #(winner? (player-squares board %))
            square-taken? #(not= nil (nth board (dec %)))
            next-player #(if (= x current-player) o x)]
        (if (or (full? board) (won? :x) (won? :o))
          board
          (do
            (redraw ui board)
            (let [next-move (next-move current-player board)]
              (if (square-taken? next-move)
                (recur board current-player)
                (let [new-board (take-square next-move {:board board :player (symbol-for current-player)})]
              (recur new-board (next-player)))))))))))

