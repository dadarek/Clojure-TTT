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
                nil
      )
    )
  )
  (it "Returns 1 for moves that win"
    (should= '(1 1 1)
              (score
                #{0 1 2}
                nil
                nil
                (fn [move board player] true)
                (fn [move board player] false)
                nil
      )
    )
  )
  (it "Returns sum of children moves that don't win or tie"
    (should= '(2 2 2)
              (score
                #{0 1 2}
                nil
                nil
                (fn [move board player] false)
                (fn [move board player] false)
                (fn [move board player] 2)
      )
    )
  )
)

(run-specs)
