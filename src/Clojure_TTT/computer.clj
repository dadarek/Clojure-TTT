(ns clojure_ttt.computer
  (:use clojure_ttt.generic_minimax)
  (:use clojure_ttt.runner))

(defrecord Computer [x-or-o]
  Player
  (next-move [player board]
    (let [scores (score nil nil nil nil nil)
          top (reduce max scores)]
      (inc (.indexOf scores top))
    )))

