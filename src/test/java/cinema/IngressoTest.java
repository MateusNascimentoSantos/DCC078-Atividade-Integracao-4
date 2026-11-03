package cinema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngressoTest {

    @Test
    void deveEmitirIngressoInteiraComTabelaNormal() {
        Ingresso ingresso = IngressoFactory.obterIngresso("Inteira");
        ingresso.setSessao("T1");
        ingresso.setPoltrona("A1");
        ingresso.setPrecoBase(32.0f);
        ingresso.setPoliticaPreco(new TabelaNormal());

        assertEquals("Inteira{sessao='T1', poltrona='A1', valor=32.0}", ingresso.emitir());
    }

    @Test
    void deveEmitirIngressoMeiaComTabelaNormal() {
        Ingresso ingresso = IngressoFactory.obterIngresso("Meia");
        ingresso.setSessao("T2");
        ingresso.setPoltrona("A1");
        ingresso.setDocumento("RG-123");
        ingresso.setPrecoBase(32.0f);
        ingresso.setPoliticaPreco(new TabelaNormal());

        assertEquals("Meia{sessao='T2', poltrona='A1', valor=16.0}", ingresso.emitir());
    }

    @Test
    void deveEmitirIngressoCortesiaComTabelaNormal() {
        Ingresso ingresso = IngressoFactory.obterIngresso("Cortesia");
        ingresso.setSessao("T3");
        ingresso.setPoltrona("A1");
        ingresso.setDocumento("VC-999");
        ingresso.setPrecoBase(32.0f);
        ingresso.setPoliticaPreco(new TabelaNormal());

        assertEquals("Cortesia{sessao='T3', poltrona='A1', valor=0.0}", ingresso.emitir());
    }

    @Test
    void deveEmitirIngressoInteiraEmDiaPromocional() {
        Ingresso ingresso = IngressoFactory.obterIngresso("Inteira");
        ingresso.setSessao("T4");
        ingresso.setPoltrona("A1");
        ingresso.setPrecoBase(32.0f);
        ingresso.setPoliticaPreco(new DiaPromocional());

        assertEquals("Inteira{sessao='T4', poltrona='A1', valor=12.0}", ingresso.emitir());
    }

    @Test
    void deveEmitirIngressoMeiaEmDiaPromocional() {
        Ingresso ingresso = IngressoFactory.obterIngresso("Meia");
        ingresso.setSessao("T5");
        ingresso.setPoltrona("A1");
        ingresso.setDocumento("RG-123");
        ingresso.setPrecoBase(32.0f);
        ingresso.setPoliticaPreco(new DiaPromocional());

        assertEquals("Meia{sessao='T5', poltrona='A1', valor=6.0}", ingresso.emitir());
    }

    @Test
    void deveEmitirIngressoInteiraEmPreEstreia() {
        Ingresso ingresso = IngressoFactory.obterIngresso("Inteira");
        ingresso.setSessao("T6");
        ingresso.setPoltrona("A1");
        ingresso.setPrecoBase(32.0f);
        ingresso.setPoliticaPreco(new PreEstreia());

        assertEquals("Inteira{sessao='T6', poltrona='A1', valor=42.0}", ingresso.emitir());
    }

    @Test
    void deveEmitirIngressoMeiaEmPreEstreia() {
        Ingresso ingresso = IngressoFactory.obterIngresso("Meia");
        ingresso.setSessao("T7");
        ingresso.setPoltrona("A1");
        ingresso.setDocumento("RG-123");
        ingresso.setPrecoBase(32.0f);
        ingresso.setPoliticaPreco(new PreEstreia());

        assertEquals("Meia{sessao='T7', poltrona='A1', valor=21.0}", ingresso.emitir());
    }

    @Test
    void deveObterCadaTipoDeIngressoPelaFabrica() {
        assertEquals("Inteira", IngressoFactory.obterIngresso("Inteira").getTipo());
        assertEquals("Meia", IngressoFactory.obterIngresso("Meia").getTipo());
        assertEquals("Cortesia", IngressoFactory.obterIngresso("Cortesia").getTipo());
    }

    @Test
    void naoDeveObterIngressoInexistente() {
        try {
            IngressoFactory.obterIngresso("Vip");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Ingresso inexistente", e.getMessage());
        }
    }

    @Test
    void naoDeveEmitirMeiaSemDocumento() {
        try {
            Ingresso ingresso = IngressoFactory.obterIngresso("Meia");
            ingresso.setSessao("T8");
            ingresso.setPoltrona("A1");
            ingresso.setPrecoBase(32.0f);
            ingresso.setPoliticaPreco(new TabelaNormal());
            ingresso.emitir();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Meia-entrada sem documento", e.getMessage());
        }
    }

    @Test
    void naoDeveEmitirCortesiaSemVoucher() {
        try {
            Ingresso ingresso = IngressoFactory.obterIngresso("Cortesia");
            ingresso.setSessao("T9");
            ingresso.setPoltrona("A1");
            ingresso.setPrecoBase(32.0f);
            ingresso.setPoliticaPreco(new TabelaNormal());
            ingresso.emitir();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Cortesia sem voucher", e.getMessage());
        }
    }

    @Test
    void naoDeveEmitirDoisIngressosParaAMesmaPoltrona() {
        Ingresso primeiro = IngressoFactory.obterIngresso("Inteira");
        primeiro.setSessao("T10");
        primeiro.setPoltrona("A1");
        primeiro.setPrecoBase(32.0f);
        primeiro.setPoliticaPreco(new TabelaNormal());
        primeiro.emitir();

        try {
            Ingresso segundo = IngressoFactory.obterIngresso("Inteira");
            segundo.setSessao("T10");
            segundo.setPoltrona("A1");
            segundo.setPrecoBase(32.0f);
            segundo.setPoliticaPreco(new TabelaNormal());
            segundo.emitir();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Poltrona ja reservada", e.getMessage());
        }
    }

    @Test
    void deveRegistrarReservaNaCentral() {
        Ingresso ingresso = IngressoFactory.obterIngresso("Inteira");
        ingresso.setSessao("T11");
        ingresso.setPoltrona("A1");
        ingresso.setPrecoBase(32.0f);
        ingresso.setPoliticaPreco(new TabelaNormal());
        ingresso.emitir();

        assertTrue(CentralReservas.getInstance().estaReservada("T11", "A1"));
        assertFalse(CentralReservas.getInstance().estaReservada("T11", "Z9"));
    }

    @Test
    void deveRetornarSempreAMesmaCentralReservas() {
        assertSame(CentralReservas.getInstance(), CentralReservas.getInstance());
    }

}
