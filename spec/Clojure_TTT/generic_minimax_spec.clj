(ns clojure_ttt.generic_minimax-spec
  (:use [clojure_ttt.generic_minimax])
  (:use [speclj.core]))

(describe "Generic Minimax"
  (it "Returns 0 on moves that tie"
    (should= '(0 0 0) 
              (score 
                #{0 1 2} 
                nil
                nil
                (fn [move board player] false)
                (fn [move board player] true)
      )
    )
  )

  (it "Returns 1 for moves that win"
    (let [board '(0 1 0)]
      (should= '(0 1 0) 
                (score 
                  #{0 1 2} 
                  board
                  nil
                  (fn [move b player] (= 1 (nth b move)))
                  (fn [move b player] (= -1 (nth b move)))
        )
      )
    )
  )
)

(run-specs)
