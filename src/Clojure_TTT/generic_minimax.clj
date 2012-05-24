(ns clojure_ttt.generic_minimax)


(defn score [moves context wins? ties? sum_of_children]
  (map
    (fn [move]
      (if (wins? move context) 1
        (if (ties? move context) 0
          (sum_of_children move context)
        )
      )
    )
    moves
  )
)
