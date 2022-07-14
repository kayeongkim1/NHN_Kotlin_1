
/**
 * 목표: 실습1 심화
 * 조건
 * 1. 색상(RGB)을 추가로 가지게 변경
 *  단 색상은 이넘으로 관리해라
 *  단, 여러가지 색깔을 가질 수 있다. 추가 삭제 가능, 중복 X
 */

fun main() {
    val product = Product("상품")
    println(product)

    // 가격, 색상을 scope function으로 설정
    // 단, 재고는 2개
    product.apply {
        //println(it)
        price = 11111
        amount = 2
        addColor(Color.RED)

    }
    println(product)


    // 물건 1건 구매를 scope function으로 실행
    product.let {
        it.buy(1)
    }
    println(product)

    // 물건 현재 가치(price*amount) println()으로 출력 - scope function 활요
    product.let{
        println("물건 현재 가치 : ${it.price * it.amount}")
        println("==============")
    }


    // 물건 1건 구매를 scope function으로 실행하고, chaining하여 물건갯구가 0이하면 "재고없음" 출력하기
    with(product) {
        product.buy(1)
    }.also {
        if (product.amount <= 0) {
            println("재고 없음")
        }
    }
    println(product)


}

enum class Color() {
    RED, GREEN, BLUE
}

private data class Product (
    val name: String,
    val color: MutableSet<Color> = mutableSetOf(),
    var price: Int = 0,
    var amount: Int = 0
) {
    fun buy(amount: Int = 1) {
        this.amount -= amount
    }

    fun addColor(color: Color) {
        this.color.add(color)
    }

    override fun toString(): String {
        return """
            상품명 : $name
            컬러  : $color
            가격  : $price
            재고  : $amount
            "=============="
        """.trimIndent()
    }
}

