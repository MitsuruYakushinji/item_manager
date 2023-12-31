package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.repository.ItemRepository;


@Service
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	// データの全件取得のためのメソッド
	public List<Item> findAll(){
		return this.itemRepository.findAll();
	}
	
	// データ保存用のメソッド
	public Item save(ItemForm itemForm) {
		
		// Entityクラスのインスタンス生成
		Item item = new Item();
		
		// フィールドのセットを実施
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		item.setCategoryId(itemForm.getCategoryId());
		// 新規登録時は在庫数に0をセットする
        item.setStock(0);


		// reposotory.saveメソッドを利用してデータの保存を行う
	    return this.itemRepository.save(item);
	}
	
	// IDカラムでデータ検索するメソッド
	public Item findById(Integer id) {
		
		Optional<Item> optionalItem = this.itemRepository.findById(id);
		
		Item item = optionalItem.get();
		
		return item;
	}
	
	// データ更新用メソッド
	public Item update(Integer id, ItemForm itemForm) {
		
		// データ1件分のEntityクラスを取得
		Item item = this.findById(id);
		
		// Formクラスのフィールドをセット
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		item.setCategoryId(itemForm.getCategoryId());
		
		// reposotory.saveメソッドを利用してデータの保存を行う
		return this.itemRepository.save(item);
	}
	
	// データ削除用のメソッド
	// saveメソッドはEntityを返り値に持つの戻り値をItem型に変更
	public Item delete(Integer id) {
		
		// idから該当のEntityクラスを取得
		Item item = this.findById(id);
		
		// EntityクラスのdeletedAtフィールドを現在日時で上書き
		item.setDeletedAt(LocalDateTime.now());
		
		// 更新処理
		return this.itemRepository.save(item);
	}
	
	public List<Item> findByDeletedAtIsNull(){
		return this.itemRepository.findByDeletedAtIsNull();
	}
	
	// 入荷処理
    public Item nyuka(Integer id, Integer inputValue) {
        Item item = this.findById(id);
        // 商品の在庫数に対して入力値分加算する
        item.setStock(item.getStock() + inputValue);
        // 在庫数の変動を保存
        return this.itemRepository.save(item);
    }

    // 出荷処理
    public Item shukka(Integer id, Integer inputValue) {
        Item item = this.findById(id);
        // 入力値が在庫数以内かを判定する
        if (inputValue <= item.getStock()) {
            // 在庫数から入力値を引いた値をセットする
            item.setStock(item.getStock() - inputValue);
        }

        // 在庫数の変動を保存
        return this.itemRepository.save(item);
    }
}




