(ns clojure_ttt.generic_minimax-spec
  (:use [clojure_ttt.generic_minimax])
  (:use [speclj.core]))

(describe "Generic Minimax"
  (it "Returns 0 on moves that tie"
    (should= '(0 1 1) (score #{0 1 2} nil
                        (fn [move context] (not= 0 move))
                        (fn [move context] (= 0 move))
                        nil)
    )
  )

  (it "Returns 1 for moves that win"
    (should= '(0 1 0) (score #{0 1 2} nil
                        (fn [move context] (= 1 move))
                        (fn [move context] (not= 1 move))
                        nil)
    )
  )

  (it "Sums scores of immediate child moves"
    (should= '(1 1)
             (score #{0 1} nil
                (fn [move context]
                  (= :context context))
                (fn [move context] false)
                (fn [move context] :context))
    )
  )

  (it "Sums scores of all grandchildren moves"
    (should= '(2 2 2)
             (score #{0 1 2} nil
                (fn [move context]
                  (= :second-context context))
                (fn [move context] false)
                (fn [move context]
                  (if (nil? context) :first-context :second-context))
              )
    )
  )
)

(run-specs)
