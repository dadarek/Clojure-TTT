(ns clojure_ttt.generic_minimax-spec
  (:use [clojure_ttt.generic_minimax])
  (:use [speclj.core]))

(describe "Generic Minimax"
  (it "Returns 0 on moves that tie"
    (should= '(0 0 0) (score #{0 1 2} nil
                        (fn [move context] false)
                        (fn [move context] true)
                        nil)
    )
  )

  (it "Returns 1 for moves that win"
    (should= '(1 1 1) (score #{0 1 2} nil
                        (fn [move context] true)
                        nil
                        nil)
    )
  )

  (it "Sums scores of immediate child moves"
    (should= '(1 1)
             (score #{0 1} nil
                (fn [move context]
                  (if (= :context context) 1))
                (fn [move context] false)
                (fn [move context] :context))
    )
  )
)

(run-specs)
