(ns clojure_ttt.generic_minimax)


(defn score [moves context wins? ties? next-context]
  (map
    (fn [move]
      (if (wins? move context) 1
        (if (ties? move context) 0
          (- (reduce + (score
                   (disj moves move)
                   (next-context move context)
                   wins?
                   ties?
                   next-context))))))
    moves))
