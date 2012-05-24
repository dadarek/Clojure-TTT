(ns clojure_ttt.generic_minimax)


(defn score [moves context wins? ties? sum_of_children]
  (map
    (fn [move]
      (if (wins? move :a) 1
        (if (ties? move :a) 0
          (sum_of_children move :a)
        )
      )
    )
    moves
  )
)
