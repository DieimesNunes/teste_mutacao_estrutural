import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;

public class BioClusterManagerTest {

        // T01 - Lista de observações vazia
        @Test
        void T01_deveRetornarListaVaziaQuandoNaoHaObservacoes() {
                BioClusterManager manager = new BioClusterManager();
                List<Observation> observacoes = new ArrayList<>();

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                10.0,
                                50.0,
                                false,
                                10);

                assertTrue(resultado.isEmpty());
        }

        // T02 - Lista com apenas uma observação
        @Test
        void T02_deveRetornarListaVaziaQuandoHaApenasUmaObservacao() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                10.0,
                                50.0,
                                false,
                                10);

                assertTrue(resultado.isEmpty());
        }

        // T03 - Duas observações próximas, da mesma espécie, com saúde adequada e não
        // invasoras
        @Test
        void T03_deveCriarConexaoQuandoObservacoesSaoValidas() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, false),
                                new Observation(2, 100, 3.0, 4.0, 70.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                6.0,
                                50.0,
                                false,
                                10);

                assertEquals(1, resultado.size());
                assertEquals("Cluster:1-2", resultado.get(0));
        }

        // T04 - Duas observações com distância maior que o raio
        @Test
        void T04_naoDeveCriarConexaoQuandoDistanciaForMaiorQueRaio() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, false),
                                new Observation(2, 100, 10.0, 0.0, 70.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                5.0,
                                50.0,
                                false,
                                10);

                assertTrue(resultado.isEmpty());
        }

        // T05 - Duas observações com distância exatamente igual ao raio
        @Test
        void T05_naoDeveCriarConexaoQuandoDistanciaForIgualAoRaio() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, false),
                                new Observation(2, 100, 3.0, 4.0, 70.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                5.0,
                                50.0,
                                false,
                                10);

                assertTrue(resultado.isEmpty());
        }

        
        // T06 - Duas observações de espécies diferentes com modoInter = false
        @Test
        void T06_naoDeveCriarConexaoEntreEspeciesDiferentesComModoInterFalso() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, false),
                                new Observation(2, 200, 3.0, 4.0, 70.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                6.0,
                                50.0,
                                false,
                                10);

                assertTrue(resultado.isEmpty());
        }

        
        // T07 - Duas observações de espécies diferentes com modoInter = true
        @Test
        void T07_deveCriarConexaoEntreEspeciesDiferentesComModoInterVerdadeiro() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, false),
                                new Observation(2, 200, 3.0, 4.0, 70.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                6.0,
                                50.0,
                                true,
                                10);

                assertEquals(1, resultado.size());
                assertEquals("Cluster:1-2", resultado.get(0));
        }

        
        // T08 - Duas observações com saúde abaixo ou igual ao threshold
        @Test
        void T08_naoDeveCriarConexaoQuandoSaudeEstaAbaixoOuIgualAoThreshold() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 50.0, false),
                                new Observation(2, 100, 3.0, 4.0, 40.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                6.0,
                                50.0,
                                false,
                                10);

                assertTrue(resultado.isEmpty());
        }

        
        // T09 - Comportamento observado: saúde da segunda observação não é considerada
        @Test
        void T09_naoCriaConexaoQuandoApenasSegundaObservacaoTemSaudeAcimaDoThreshold() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 40.0, false),
                                new Observation(2, 100, 3.0, 4.0, 80.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                6.0,
                                50.0,
                                false,
                                10);

                assertTrue(resultado.isEmpty());
        }

        
        // T10 - Duas observações invasoras ao mesmo tempo
        @Test
        void T10_naoDeveCriarConexaoQuandoAsDuasObservacoesSaoInvasoras() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, true),
                                new Observation(2, 100, 3.0, 4.0, 70.0, true));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                6.0,
                                50.0,
                                false,
                                10);

                assertTrue(resultado.isEmpty());
        }

        
        // T11 - Várias conexões possíveis com limiteSeguranca = 1
        @Test
        void T11_deveRetornarApenasUmaConexaoQuandoLimiteSegurancaForUm() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, false),
                                new Observation(2, 100, 1.0, 1.0, 70.0, false),
                                new Observation(3, 100, 2.0, 2.0, 90.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                10.0,
                                50.0,
                                false,
                                1);

                assertEquals(1, resultado.size());
        }

        
        // T12 - Apenas uma observação invasora
        @Test
        void T12_deveCriarConexaoQuandoApenasUmaObservacaoForInvasora() {
                BioClusterManager manager = new BioClusterManager();

                List<Observation> observacoes = List.of(
                                new Observation(1, 100, 0.0, 0.0, 80.0, true),
                                new Observation(2, 100, 3.0, 4.0, 70.0, false));

                List<String> resultado = manager.processarClusters(
                                observacoes,
                                6.0,
                                50.0,
                                false,
                                10);

                assertEquals(1, resultado.size());
                assertEquals("Cluster:1-2", resultado.get(0));
        }
}