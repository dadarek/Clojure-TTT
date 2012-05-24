(ns clojure_ttt.generic_minimax)


(defn score [moves board player won? tie?] 
  (map 
    (fn [move] 
      (if (won? move board player) 1 
        (if (tie? move board player) 0 0)
      )
    ) 
    moves
  )
)
