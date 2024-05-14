package mk.ukim.finki.emt.sharedkernel.domain.finfancial;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;


@Embeddable
@Getter

public class Money implements ValueObject {
    @Enumerated(value = EnumType.STRING)
    private final Currency currency;
    private final double amount;

    protected Money(){
        this.amount=0.0;
        this.currency=null;

    }
 public Money (mk.ukim.finki.emt.sharedkernel.domain.finfancial.Currency currency, @NonNull double amount){
        this.amount=amount;
        this.currency=currency;
 }

 public static Money valueOf(mk.ukim.finki.emt.sharedkernel.domain.finfancial.Currency currency, int amount){
        return new Money(currency,amount);
 }

    public Money add(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount + money.amount);
    }

    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount - money.amount);
    }

    public Money multiply(int m)  {
        return new Money(currency,amount*m);
    }




}
