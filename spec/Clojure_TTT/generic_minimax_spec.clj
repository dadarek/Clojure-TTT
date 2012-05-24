(ns clojure_ttt.generic_minimax-spec
  (:use [clojure_ttt.generic_minimax])
  (:use [speclj.core]))

(describe "Generic Minimax"
  (it "Returns 0 on moves that tie"
    (should= '(0 0 0)
              (score
                #{0 1 2}
                :context
                (fn [move context] false)
                (fn [move context] true)
                nil
      )
    )
  )
  (it "Returns 1 for moves that win"
    (should= '(1 1 1)
              (score
                #{0 1 2}
                :context
                (fn [move context] true)
                nil
                nil
      )
    )
  )
  (it "Returns sum of children moves that don't win or tie"
    (should= '(2 2 2)
              (score
                #{0 1 2}
                :context
                (fn [move context] false)
                (fn [move context] false)
                (fn [move context] 2)
      )
    )
  )
  (it "Passes context to injected functions"
      ; force this without an external should
    (should= '(3 3 3)
              (score
                #{0 1 2}
                :context
                (fn [move context] (should= :context context) false)
                (fn [move context] (should= :context context) false)
                (fn [move context] (should= :context context) 3)
      )
    )
  )
)

(run-specs)
