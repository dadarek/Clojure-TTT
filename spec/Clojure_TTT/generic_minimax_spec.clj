(ns clojure_ttt.generic_minimax-spec
  (:use [clojure_ttt.generic_minimax])
  (:use [speclj.core]))

(describe "Generic Minimax"
  (it "Returns 0 on moves that tie"
    (should= '(0 0 0) (score #{0 1 2} nil
                        (fn [move context] false)
                        (fn [move context] true)
                        nil
                        nil)
    )
  )

  (it "Returns 1 for moves that win"
    (should= '(1 1 1) (score #{0 1 2} nil
                        (fn [move context] true)
                        nil
                        nil
                        nil)
    )
  )

  (it "Callsback next-context"
    (should-throw (dorun (score #{0} nil
                        (fn [move context] false)
                        (fn [move context] false)
                        (fn [move context] 0)
                        (fn [move context] (throw "Good"))))
    )
  )

  ;TODO: dump this after you stop calling sum-of-children
  (it "Returns sum of children for moves that don't win or tie"
    (dorun (score #{0 1 2} nil
              (fn [move context] false)
              (fn [move context] false)
              (fn [move context] 2)
              (fn [move context] context))
    )
  )

  (it "Passes context back to the injected functions"
    (dorun (score [0] :context
              (fn [move context] (should= :context context) false)
              (fn [move context] (should= :context context) false)
              (fn [move context] (should= :context context) 3)
              (fn [move context] (should= :context context) context))
    )
  )
)

(run-specs)
