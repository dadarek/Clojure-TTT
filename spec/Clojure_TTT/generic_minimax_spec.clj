(ns clojure_ttt.generic_minimax-spec
  (:use [clojure_ttt.generic_minimax])
  (:use [speclj.core]))

(describe "Generic Minimax"
  (it "Returns 0 on moves that tie"
    (let [winning-moves (fn [move context] (not= 0 move))
          tie-moves (fn [move context] (= 0 move))]
      (should= '(0 1 1)
               (score #{0 1 2} nil winning-moves tie-moves nil))
    )
  )

  (it "Returns 1 for moves that win"
    (let [winning-moves (fn [move context] (= 1 move))
          tie-moves (fn [move context] (not= 1 move))]
      (should= '(0 1 0)
               (score #{0 1 2} nil winning-moves tie-moves nil))
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
