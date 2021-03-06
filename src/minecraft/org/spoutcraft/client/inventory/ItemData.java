/*
 * This file is part of Spoutcraft (http://www.spout.org/).
 *
 * Spoutcraft is licensed under the SpoutDev License Version 1.
 *
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev license version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spoutcraft.client.inventory;

import java.util.HashMap;

public class ItemData {
	public final int id;
	public final short data;
	private static final HashMap<Integer, HashMap<Integer, ItemData>> itemDatas= new HashMap<Integer, HashMap<Integer, ItemData>>();
	protected ItemData(int id) {
		this.id = id;
		this.data = 0;
	}

	private ItemData(int id, int data) {
		this.id = id;
		this.data = (short)data;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ItemData) {
			ItemData temp = (ItemData)obj;
			return temp.id == id && temp.data == data;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 37 * id * 7 * (2 + data);
	}

	public static ItemData getItemData(int id) {
		return getItemData(id, 0);
	}

	public static ItemData getItemData(int id, int data) {
		HashMap<Integer, ItemData> itemDatasForId = itemDatas.get(id);
		if (itemDatasForId == null) {
			itemDatasForId = new HashMap<Integer, ItemData>();
			itemDatas.put(id, itemDatasForId);
		}
		ItemData itemData = itemDatasForId.get(data);
		if (itemData == null) {
			itemData = new ItemData(id, data);
			itemDatasForId.put(data, itemData);
		}
		return itemData;
	}
}
