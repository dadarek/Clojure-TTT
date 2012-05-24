(ns clojure_ttt.minimax_injections
  (:use clojure.set)
  (:use clojure_ttt.rules))


(defn player-squares [board player]
  ( let [owns? #(= player (nth board %))
         not-nil? #(not= nil %)
         index-squares #(if (owns? %) (inc %))]
    (set
      (filter not-nil?
        (map index-squares (range 0 9))
      )
    )
  )
)

(defn wins? [square context]
  (winner? (conj (player-squares
                   (:board context)
                   (:player context))
           square))
)

(defn ties? [square context]
  true
)
