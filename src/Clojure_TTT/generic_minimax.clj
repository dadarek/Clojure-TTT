(ns clojure_ttt.generic_minimax)


(defn score [moves context wins? ties? sum-of-children next-context]
  (map
    (fn [move]
      (if (wins? move context) 1
        (if (ties? move context) 0
          (sum-of-children move (next-context move context))
        )
      )
    )
    moves
  )
)
