(ns clojure_ttt.computer
  (:use clojure_ttt.generic_minimax)
  (:use clojure_ttt.minimax_injections)
  (:use clojure_ttt.board_utilities)
  (:use clojure_ttt.runner))

(defrecord Computer [x-or-o]
  Player
  (next-move [player board]
    (let [possible-moves (get-empty-squares board)
          context {:board board :player x-or-o}
          scores (score possible-moves context
                        wins? ties? next-context)
          top-score (reduce max scores)
          top-score-index (.indexOf scores top-score)]
      (nth (seq possible-moves) top-score-index)
    )))

