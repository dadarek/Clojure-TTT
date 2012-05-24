(ns clojure_ttt.generic_minimax)


(defn score [moves board player won? tie? sum_of_children]
  (map
    (fn [move]
      (if (won? move board player) 1
        (if (tie? move board player) 0
          (sum_of_children move board player)
        )
      )
    )
    moves
  )
)
