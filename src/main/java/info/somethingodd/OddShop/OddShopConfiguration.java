/* This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.somethingodd.OddShop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Gordon Pettey (petteyg359@gmail.com)
 */
public class OddShopConfiguration {
    private OddShop oddShop;

    public OddShopConfiguration(OddShop oddShop) {
        this.oddShop = oddShop;
    }

    public void configure() {

    }

    private void initialConfig(String filename) throws IOException {
        File file = new File(oddShop.getDataFolder(), filename);
        if (!file.exists()) {
            BufferedReader src = null;
            BufferedWriter dst = null;
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                src = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + filename)));
                dst = new BufferedWriter(new FileWriter(file));
                String line = src.readLine();
                while (line != null) {
                    dst.write(line + "\n");
                    line = src.readLine();
                }
                src.close();
                dst.close();
                oddShop.log.info("Wrote default " + filename);
            } catch (IOException e) {
                oddShop.log.warning("Error writing default " + filename);
            } finally {
                try {
                    src.close();
                    dst.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
