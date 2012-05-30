(ns clojure_ttt.computer
  (:use clojure_ttt.generic_minimax)
  (:use clojure_ttt.minimax_injections)
  (:use clojure_ttt.board_utilities)
  (:use clojure_ttt.runner))

(defrecord Computer [x-or-o]
  Player
  (next-move [player board]
    (let [empty-squares (get-empty-squares board)
          scores (score empty-squares x-or-o
                        wins? ties? next-context)
          top (reduce max scores)]
      (inc (.indexOf scores top))
    )))

